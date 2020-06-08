package personal.dongxia.android.multimeter

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.media.ImageReader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import android.util.Size
import android.util.SparseArray
import android.util.SparseIntArray
import android.view.Surface
import android.view.TextureView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import personal.dongxia.android.multimeter.databinding.ActivityCameraBinding
import java.io.File

class CameraActivity : AppCompatActivity() {

    companion object {
        val ORIENTATIONS = SparseIntArray().apply {
            append(Surface.ROTATION_0, 90)
            append(Surface.ROTATION_90, 0)
            append(Surface.ROTATION_180, 270)
            append(Surface.ROTATION_270, 180)
        }
    }

    private lateinit var binding : ActivityCameraBinding

    var cameraId : String = ""
    var cameraDevice: CameraDevice? = null
    var cameraCaptureSession: CameraCaptureSession? = null
    var captureRequest: CaptureRequest? = null
    var captureRequestBuilder: CaptureRequest.Builder? = null
    lateinit var surfaceTextureListener: TextureView.SurfaceTextureListener

    var imageDimensions: Size? = null
    var imageReader: ImageReader? = null
    var file: File? = null
    var backgroundHandler: Handler? = null
    var backgroundThread: HandlerThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
                openCamera()
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
                return false
            }

            override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
            }
        }

        binding.cameraPreview.surfaceTextureListener = surfaceTextureListener
        binding.btnCapture.setOnClickListener {
            takePicture()
        }
    }

    override fun onResume() {
        super.onResume()
        startBackgroundThread()
        if (binding.cameraPreview.isAvailable) {
            openCamera()
        } else {
            binding.cameraPreview.surfaceTextureListener = surfaceTextureListener
        }
    }

    private fun startBackgroundThread() {
        backgroundThread = HandlerThread("camera background")
        backgroundThread!!.start()
        backgroundHandler = Handler(backgroundThread!!.looper)
    }

    override fun onPause() {
        super.onPause()
        stopBackgroundThread()
    }

    private fun openCamera() {
        val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraId = manager.cameraIdList[0]
        val cameraCharacteristics = manager.getCameraCharacteristics(cameraId)
        val map = cameraCharacteristics[CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP]
        imageDimensions = map!!.getOutputSizes(SurfaceTexture::class.java)[0]
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
            return
        }
        manager.openCamera(cameraId, object: CameraDevice.StateCallback() {
            override fun onDisconnected(camera: CameraDevice) {
                cameraDevice?.close()
                cameraDevice = null
            }

            override fun onError(camera: CameraDevice, error: Int) {
                cameraDevice?.close()
                cameraDevice = null
            }

            override fun onOpened(camera: CameraDevice) {
                cameraDevice = camera
                createCameraPreview()
            }
        }, null)
    }

    private fun takePicture() {
        if (cameraDevice == null) {
            return
        }
        val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraCharacteristics = manager.getCameraCharacteristics(cameraDevice!!.id)
        val jpegSize = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)!!.getOutputSizes(ImageFormat.JPEG)

        var width = 640
        var height = 480
        if (jpegSize != null && jpegSize.size > 0) {
            width = jpegSize[0].width
            height = jpegSize[0].height
        }
        val reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1)
        val outputSurface :MutableList<Surface> = ArrayList()
        outputSurface.add(reader.surface)
        outputSurface.add(Surface(binding.cameraPreview.surfaceTexture))
        val builder = cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
        builder.addTarget(reader.surface)
        builder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)

        val rotation = windowManager.defaultDisplay.rotation
        builder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS[rotation])
        val ts = System.currentTimeMillis()
        val file = File("${Environment.getExternalStorageDirectory()}/${ts}.jpg")

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // todo
            }
        }
    }

    private fun createCameraPreview() {
        val texture = binding.cameraPreview.surfaceTexture
        val surface = Surface(texture)
        texture.setDefaultBufferSize(imageDimensions!!.width, imageDimensions!!.height)
        captureRequestBuilder = cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
        captureRequestBuilder!!.addTarget(surface)
        cameraDevice!!.createCaptureSession(arrayOf(surface).toMutableList(), object : CameraCaptureSession.StateCallback() {
            override fun onConfigured(session: CameraCaptureSession) {
                if (cameraDevice != null) {
                    cameraCaptureSession = session
                    updatePreview()
                }
            }

            override fun onConfigureFailed(session: CameraCaptureSession) {
                Toast.makeText(this@CameraActivity, "onConfigureFailed", Toast.LENGTH_LONG).show()
            }
        }, null)
    }

    private fun updatePreview() {
        if (cameraDevice == null) {
            return
        }
        captureRequestBuilder!!.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)
        cameraCaptureSession!!.setRepeatingRequest(captureRequestBuilder!!.build(), null, backgroundHandler)
    }

    private fun stopBackgroundThread() {
        backgroundThread?.quitSafely()
        backgroundThread?.join()
        backgroundThread = null
        backgroundHandler = null
    }
}

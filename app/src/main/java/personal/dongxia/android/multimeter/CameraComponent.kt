package personal.dongxia.android.multimeter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.os.Handler
import android.os.HandlerThread
import android.view.Surface
import android.view.TextureView
import androidx.annotation.IntDef

/**
 * @author wudongxia
 * @date 2020/6/11
 */
class CameraComponent private constructor(builder: Builder) {
    private val context: Context
    private val textureView: AutoFitTextureView
    private var backgroundHandler: Handler? = null
    private var backgroundThread: HandlerThread? = null
    private var status = Status.IDLE
    private var flashStatus = FlashStatus.OFF
    private var ratio: Int = Ratio.RATIO_16_9
    private lateinit var cameraDevice: CameraDevice
    private lateinit var cameraManager: CameraManager
    private lateinit var currentCameraId: String
    private lateinit var captureRequestBuilder: CaptureRequest.Builder
    private lateinit var cameraCaptureSession: CameraCaptureSession

    @IntDef(Ratio.RATIO_16_9, Ratio.RATIO_4_3)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class Ratio {
        companion object {
            /**
             * 16比9
             */
            const val RATIO_16_9 = 1
            /**
             * 4比3
             */
            const val RATIO_4_3 = 2
        }
    }

    @IntDef(FlashStatus.ON, FlashStatus.OFF)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class FlashStatus {
        companion object {

            const val ON = 1

            const val OFF = 2
        }
    }

    companion object Status {
        const val IDLE = "IDLE"
        const val OPENING = "OPENING"
        const val PREVIEW = "DRAGGING"
        const val ENTERING = "ENTERING"
        const val ENTERED = "ENTERED"
        const val EXITING = "EXITING"
        const val ROLLING_BACK = "ROLLING_BACK"
    }

    fun init() {
        cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        // todo 判断是否有摄像头
        currentCameraId = cameraManager.cameraIdList[0]
        when (ratio) {
            Ratio.RATIO_16_9 -> textureView.setAspectRatio(9, 16)
            Ratio.RATIO_4_3 -> textureView.setAspectRatio(3, 4)
        }
    }

    fun start() {
        startCameraThread()
        if (textureView.isAvailable) {
            openCamera()
        }
        textureView.surfaceTextureListener = surfaceTextureListener
    }
    fun stop() {
        stopCameraThread()
        textureView.surfaceTextureListener = null
    }
    fun close() {

    }

    class Builder {
        private lateinit var context: Context
        private lateinit var textureView: AutoFitTextureView
        private var ratio: Int = Ratio.RATIO_16_9
        fun setContext(context: Context): Builder {
            this.context = context
            return this
        }
        fun getContext() = context
        fun getRatio() = ratio

        fun setTextureView(textureView: AutoFitTextureView): Builder {
            this.textureView = textureView
            return this
        }
        fun getTextureView() = textureView

        fun build(): CameraComponent {
            return CameraComponent(this)
        }
    }

    init {
        context = builder.getContext()
        textureView = builder.getTextureView()
        ratio = builder.getRatio()
    }


    @SuppressLint("MissingPermission")
    private fun openCamera() {
        require(status == Status.IDLE)
        status = Status.OPENING
        val cameraCharacteristics = cameraManager.getCameraCharacteristics(currentCameraId)
        val map = cameraCharacteristics[CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP]
        cameraManager.openCamera(currentCameraId, object: CameraDevice.StateCallback() {
            override fun onDisconnected(camera: CameraDevice) {

            }

            override fun onError(camera: CameraDevice, error: Int) {

            }

            override fun onOpened(camera: CameraDevice) {
                cameraDevice = camera
                createCameraPreview()
            }
        }, null)
    }
    private fun startCameraThread() {
        backgroundThread = HandlerThread("camera background").apply {
            start()
            backgroundHandler = Handler(looper)
        }
    }

    private fun stopCameraThread() {
        backgroundThread?.quitSafely()
        backgroundThread?.join()
        backgroundThread = null
        backgroundHandler = null
    }
    private fun createCameraPreview() {
        val surface = Surface(textureView.surfaceTexture)
        textureView.surfaceTexture.setDefaultBufferSize(textureView.width, textureView.height)
        captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
        captureRequestBuilder.addTarget(surface)
        cameraDevice.createCaptureSession(arrayOf(surface).toMutableList(), object : CameraCaptureSession.StateCallback() {
            override fun onConfigured(session: CameraCaptureSession) {
                cameraCaptureSession = session
                updatePreview()
            }

            override fun onConfigureFailed(session: CameraCaptureSession) {

            }
        }, null)
    }

    fun setFlashStatus(@FlashStatus flashStatus: Int) {
        this.flashStatus = flashStatus
        when (flashStatus) {
            FlashStatus.OFF -> captureRequestBuilder[CaptureRequest.FLASH_MODE] = CaptureRequest.FLASH_MODE_OFF
            FlashStatus.ON -> captureRequestBuilder[CaptureRequest.FLASH_MODE] = CaptureRequest.FLASH_MODE_TORCH
        }
        cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(), null, backgroundHandler)
    }

    fun zoom() {

    }

    private fun updatePreview() {
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)
        cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(), null, backgroundHandler)
    }

    private val surfaceTextureListener = object : TextureView.SurfaceTextureListener {
        override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
            openCamera()
        }

        override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean = true

        override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {

        }

        override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

        }
    }
}
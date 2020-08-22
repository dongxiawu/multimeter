package personal.dongxia.android.multimeter

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File

/**
 * file provider 测试页面
 */
class FileProviderActivity : AppCompatActivity() {
    companion object {
        const val RESULT_CODE_CAPTURE_IMAGE = 1

        const val IMAGE_PATH_NAME = "images"
        const val PROVIDER_AUTHORITY_NAME = "${BuildConfig.APPLICATION_ID}.provider"
    }
    lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_provider)
        findViewById<Button>(R.id.start_camera).setOnClickListener {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == RESULT_CODE_CAPTURE_IMAGE) {
            findViewById<ImageView>(R.id.image).setImageURI(uri)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val dir = File(filesDir.path + File.separator + IMAGE_PATH_NAME)
        if (dir.exists() && !dir.isDirectory) {
            dir.delete()
        } else if (!dir.exists()) {
            dir.mkdir()
        }
        val file = File(dir, "${System.currentTimeMillis()}.jpg")
        if (!file.exists()) {
            file.createNewFile()
        }
        uri = FileProvider.getUriForFile(this, PROVIDER_AUTHORITY_NAME, file)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        }
        startActivityForResult(intent, RESULT_CODE_CAPTURE_IMAGE)
    }
}

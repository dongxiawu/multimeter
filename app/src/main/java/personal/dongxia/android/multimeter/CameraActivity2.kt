package personal.dongxia.android.multimeter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import personal.dongxia.android.multimeter.databinding.ActivityCameraBinding

class CameraActivity2 : AppCompatActivity() {
    private lateinit var binding : ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cameraFragment = CameraFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_camera, cameraFragment)
                .show(cameraFragment).commit()
    }
}

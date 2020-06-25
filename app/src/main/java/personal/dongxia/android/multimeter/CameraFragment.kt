package personal.dongxia.android.multimeter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import personal.dongxia.android.multimeter.databinding.FragmentCameraBinding


class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    private lateinit var cameraComponent: CameraComponent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        cameraComponent = CameraComponent.Builder().setContext(requireContext())
                .setTextureView(binding.cameraPreview).build()
        cameraComponent.init()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        cameraComponent.start()
    }


    override fun onPause() {
        super.onPause()
        cameraComponent.stop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraComponent.close()
    }
}

package personal.dongxia.android.multimeter.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import personal.dongxia.android.multimeter.R


class PhoneNumberFragment : Fragment() {

    private val viewModel: PhoneNumberInfoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_phone_number, container, false)
        root.findViewById<Button>(R.id.btn_query).setOnClickListener {
            viewModel.setPhoneNumber("")
        }
        viewModel.phoneNumberInfo.observe(viewLifecycleOwner, Observer<PhoneNumberInfo> {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
        })
        return root
    }
}

package personal.dongxia.android.multimeter.phone

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import personal.dongxia.android.multimeter.R
import personal.dongxia.android.multimeter.phone.vo.Resource

/**
 * 一般只有带生命周期的组件可以观察 liveData
 *
 */
class PhoneNumberFragment : Fragment() {

    private val viewModel: PhoneNumberInfoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_phone_number, container, false)

        root.findViewById<Button>(R.id.btn_query).setOnClickListener {
            val editText = root.findViewById<EditText>(R.id.edit_text)
            val text = if (TextUtils.isEmpty(editText.text.toString())) {
                null
            } else {
                editText.text.toString()
            }
            viewModel.setPhoneNumber(text)
        }
        viewModel.phoneNumberInfo.observe(viewLifecycleOwner, Observer<Resource<PhoneNumberInfo>> {
            Toast.makeText(requireContext(), it.data.toString(), Toast.LENGTH_LONG).show()
        })
        return root
    }
}

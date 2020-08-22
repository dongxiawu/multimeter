package personal.dongxia.android.multimeter.district

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import personal.dongxia.android.multimeter.R
import personal.dongxia.android.multimeter.district.bean.District
import personal.dongxia.android.multimeter.district.service.querySubDistrictList

private const val ADDRESS_CODE = "addressCode"


class LocationFragment : Fragment() {
    private var parentId: String? = null
    var onItemClickListener: OnItemClickListener? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var locationAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            parentId = it.getString(ADDRESS_CODE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_location, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
        recyclerView.addItemDecoration(personal.dongxia.android.multimeter.uikit.widget.recyclerview.DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        locationAdapter = LocationAdapter()
        locationAdapter.onItemClickListener = object : LocationAdapter.OnItemClickListener {
            override fun onClick(district: District) {
                onItemClickListener?.onClick(district)
            }
        }
        recyclerView.adapter = locationAdapter
        parentId?.let {
            loadData(it)
        }
        return root
    }

    private fun loadData(parentId: String) {
        Thread(Runnable {
            val result = querySubDistrictList(parentId)
            recyclerView.post {
                locationAdapter.locationList.clear()
                locationAdapter.locationList.addAll(result.data)
                locationAdapter.notifyDataSetChanged()
            }
        }).start()
    }

    companion object {
        @JvmStatic
        fun newInstance(addressCode: String) =
                LocationFragment().apply {
                    arguments = Bundle().apply {
                        putString(ADDRESS_CODE, addressCode)
                    }
                }
    }


    interface OnItemClickListener {
        fun onClick(district: District);
    }
}

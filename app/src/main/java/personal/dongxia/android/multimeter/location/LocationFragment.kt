package personal.dongxia.android.multimeter.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import personal.dongxia.android.multimeter.R
import personal.dongxia.android.multimeter.location.model.Location
import personal.dongxia.android.multimeter.location.request.query

private const val PARENT_ID = "parentId"


class LocationFragment : Fragment() {
    private var parentId: String? = null
    var onItemClickListener: OnItemClickListener? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var locationAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            parentId = it.getString(PARENT_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_location, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
        recyclerView.addItemDecoration(personal.dongxia.android.multimeter.uikit.widget.recyclerview.DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        locationAdapter = LocationAdapter()
        locationAdapter.onItemClickListener = object : LocationAdapter.OnItemClickListener {
            override fun onClick(location: Location) {
                onItemClickListener?.onClick(location)
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
            val result = query(parentId)
            recyclerView.post {
                locationAdapter.locationList.clear()
                locationAdapter.locationList.addAll(result.result)
                locationAdapter.notifyDataSetChanged()
            }
        }).start()
    }

    companion object {
        @JvmStatic
        fun newInstance(parentId: String) =
                LocationFragment().apply {
                    arguments = Bundle().apply {
                        putString(PARENT_ID, parentId)
                    }
                }
    }


    interface OnItemClickListener {
        fun onClick(location: Location);
    }
}

package personal.dongxia.android.multimeter.location

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import personal.dongxia.android.business.Ip.IpServiceImpl
import personal.dongxia.android.multimeter.R
import personal.dongxia.android.multimeter.location.model.LocationResponse

private const val TAG = "LocationActivity"

class LocationActivity : AppCompatActivity() {
    private val APP_KEY = "054053e5108beb7abf26c9f303c9b518"
    private val HOST = "https://apis.juhe.cn/xzqh/query"

    private lateinit var recyclerView: RecyclerView
    private lateinit var locationAdapter: LocationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        recyclerView = findViewById(R.id.recycler_view)
        locationAdapter = LocationAdapter()
        recyclerView.adapter = locationAdapter
        Thread(Runnable {
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url("$HOST?fid=320000&key=$APP_KEY")
                    .build()
            val response = client.newCall(request).execute()
            val result = Gson().fromJson(response.body?.string(), LocationResponse::class.java)
            recyclerView.post {
                locationAdapter.locationList.clear()
                locationAdapter.locationList.addAll(result.result)
                locationAdapter.notifyDataSetChanged()
            }
            Log.d(TAG, response.body.toString())
        }).start()
    }
}

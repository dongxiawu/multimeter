package personal.dongxia.android.multimeter.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import personal.dongxia.android.multimeter.R

class PageTestActivity : AppCompatActivity() {
    private lateinit var viewModel: PageTestViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PageTestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_test)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(PageTestViewModel::class.java)
        recyclerView = findViewById(R.id.recycler_view)
        adapter = PageTestAdapter()
        viewModel.data.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
    }
}

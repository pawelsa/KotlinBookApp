package com.example.kotlin_bookapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_bookapp.R
import com.example.kotlin_bookapp.domain.commands.RequestForecastCommand
import com.example.kotlin_bookapp.extensions.DelegateExt
import com.example.kotlin_bookapp.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private val zipCode: Long by DelegateExt.preference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        attachToScroll(forecastList)

        forecastList.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync {
        val result = RequestForecastCommand(zipCode).execute()
        uiThread {
            forecastList.adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
            }
            toolbarTitle = "${result.city} (${result.country})"
        }
    }
}

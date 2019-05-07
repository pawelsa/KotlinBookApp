package com.example.kotlin_bookapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.kotlin_bookapp.domain.commands.RequestDayForecastCommand
import com.example.kotlin_bookapp.domain.model.Forecast
import com.example.kotlin_bookapp.extensions.color
import com.example.kotlin_bookapp.extensions.textColor
import com.example.kotlin_bookapp.extensions.toDateString
import com.example.kotlin_bookapp.ui.activities.ToolbarManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.text.DateFormat

class DetailActivity : AppCompatActivity(), ToolbarManager {

    companion object {
        const val ID = "DetailActivity:id"
        const val CITY_NAME = "DetailActivity:cityName"
    }

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()
        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }

        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread {
                bindForecast(result)
            }
        }
    }

    private fun bindForecast(result: Forecast) {
        with(result) {
            Picasso.with(ctx).load(iconUrl).into(icon)
            supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
            weatherDescription.text = description
            bindWeather(high to maxTemperature, low to minTemperature)
        }
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}"
        it.second.textColor = color(
            when (it.first) {
                in -50..0 -> android.R.color.holo_red_dark
                in 0..15 -> android.R.color.holo_orange_dark
                else -> android.R.color.holo_green_dark
            }
        )
    }
}

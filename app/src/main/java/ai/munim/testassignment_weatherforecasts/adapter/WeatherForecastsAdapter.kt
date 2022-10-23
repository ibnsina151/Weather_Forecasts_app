package ai.munim.testassignment_weatherforecasts.adapter

import ai.munim.testassignment_weatherforecasts.R
import ai.munim.testassignment_weatherforecasts.adapter.viewholders.WeatherForecastsViewHolder
import ai.munim.testassignment_weatherforecasts.databinding.ItemViewerWeatherInfoDetailsBinding
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherListModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class WeatherForecastsAdapter(diffCallBacks: DiffUtil.ItemCallback<WeatherListModel>) :
    ListAdapter<WeatherListModel, WeatherForecastsViewHolder?>(diffCallBacks) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastsViewHolder {
        val binding: ItemViewerWeatherInfoDetailsBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_viewer_weather_info_details, parent, false)
        return  WeatherForecastsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherForecastsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }



    interface OnItemClickListener {
//        fun onItemClick(historyModel: HistoryModel?)
    }


}
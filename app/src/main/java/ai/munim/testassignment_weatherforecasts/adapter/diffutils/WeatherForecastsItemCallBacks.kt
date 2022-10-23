package ai.munim.testassignment_weatherforecasts.adapter.diffutils

import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherForecastsModel
import ai.munim.testassignment_weatherforecasts.domain.model.openWeatherModel.WeatherListModel
import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil

class WeatherForecastsItemCallBacks : DiffUtil.ItemCallback<WeatherListModel>() {

    override fun areItemsTheSame(oldItem: WeatherListModel, newItem: WeatherListModel): Boolean {
        return TextUtils.equals(oldItem.dtTxt,newItem.dtTxt);
    }

    override fun areContentsTheSame(oldItem: WeatherListModel, newItem: WeatherListModel): Boolean {
        return oldItem.humidity == newItem.humidity
    }

}
package ai.munim.testassignment_weatherforecasts.activities

import ai.munim.testassignment_weatherforecasts.R
import ai.munim.testassignment_weatherforecasts.helper.PermissionManager
import ai.munim.testassignment_weatherforecasts.utils.CommonTasks
import ai.munim.testassignment_weatherforecasts.viewmodels.HomeViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }




}
package com.tflcodingchallenge.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tflcodingchallenge.App
import com.tflcodingchallenge.R
import com.tflcodingchallenge.Response
import com.tflcodingchallenge.RoadStatus
import com.tflcodingchallenge.home.di.DaggerStatusComponent
import com.tflcodingchallenge.home.di.StatusModule
import kotlinx.android.synthetic.main.activity_road_status.*
import javax.inject.Inject

class RoadStatusActivity : AppCompatActivity(), RoadStatusContract.View {

    @Inject
    lateinit var statusPresenter: RoadStatusContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_road_status)

        val roadId = et_road_id.text.toString()

        DaggerStatusComponent.builder()
            .appComponent((application as App).getComponent())
            .statusModule(StatusModule(this))
            .build()
            .inject(this)

        btn_submit.setOnClickListener{
            statusPresenter.getStatus(roadId)
        }
    }



    override fun showResults(results: Response) {
        tv_display_name.text = results.response.get(0).displayName
        tv_status_severity.text = results.response.get(0).statusSeverity
        tv_status_severity_description.text = results.response.get(0).statusSeverityDescription
    }

    override fun showError(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }


}

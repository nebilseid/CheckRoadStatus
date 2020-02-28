package com.tflcodingchallenge.home

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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



        DaggerStatusComponent.builder()
            .appComponent((application as App).getComponent())
            .statusModule(StatusModule(this))
            .build()
            .inject(this)

        btn_submit.setOnClickListener{
            val roadId = et_road_id.text.toString()
            statusPresenter.getStatus(roadId)
        }
    }

    override fun showResults(results: List<RoadStatus>) {
        tv_error.visibility = View.GONE
        gr_view.visibility = View.VISIBLE

        tv_display_name.text = results.get(0).displayName
        tv_status_severity.text = results.get(0).statusSeverity
        tv_status_severity_description.text = results.get(0).statusSeverityDescription
    }

    override fun showError(message: String) {
        gr_view.visibility = View.GONE
        tv_error.visibility = View.VISIBLE
        tv_error.text = message
    }


}

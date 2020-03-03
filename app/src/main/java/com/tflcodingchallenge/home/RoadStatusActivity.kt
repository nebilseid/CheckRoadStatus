package com.tflcodingchallenge.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tflcodingchallenge.App
import com.tflcodingchallenge.R
import com.tflcodingchallenge.model.RoadStatus
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
            .statusModule(StatusModule())
            .build()
            .inject(this)

        statusPresenter.attachView(this)
        btn_submit.setOnClickListener {
            pb_raod_status.visibility = View.VISIBLE
            val roadId = et_road_id.text.toString()
            statusPresenter.getStatus(roadId)
        }
    }

    override fun showResults(results: List<RoadStatus>) {
        pb_raod_status.visibility = View.GONE
        tv_error.visibility = View.GONE
        gr_view.visibility = View.VISIBLE

        tv_display_name.text = results[0].displayName
        tv_status_severity.text = results[0].statusSeverity
        tv_status_severity_description.text = results[0].statusSeverityDescription
    }

    override fun showError(error: String) {
        pb_raod_status.visibility = View.GONE
        gr_view.visibility = View.GONE
        tv_error.visibility = View.VISIBLE
        tv_error.text = error

    }

    override fun onDestroy() {
        statusPresenter.detachView()
        super.onDestroy()
    }
}

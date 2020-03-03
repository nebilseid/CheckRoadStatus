package com.tflcodingchallenge

import com.tflcodingchallenge.base.Presenter
import com.tflcodingchallenge.home.RoadStatusPresenter
import com.tflcodingchallenge.model.RoadStatus
import com.tflcodingchallenge.network.RoadStatusService
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(BlockJUnit4ClassRunner::class)
class RoadStatusPresenterTest {

    @Mock
    private lateinit var roadStatusService: RoadStatusService

    @Mock
    private lateinit var roadStatusPresenter: RoadStatusPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        roadStatusPresenter = RoadStatusPresenter(roadStatusService)
    }

    @Test
    fun `is successful - Response as result`() {



        }
    }
}

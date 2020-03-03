package com.tflcodingchallenge

import com.tflcodingchallenge.home.RoadStatusContract
import com.tflcodingchallenge.home.RoadStatusPresenter
import com.tflcodingchallenge.model.RoadStatus
import com.tflcodingchallenge.network.RoadStatusService
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(BlockJUnit4ClassRunner::class)
class RoadStatusPresenterTest {

    @Mock
    private lateinit var roadStatusService: RoadStatusService

    private lateinit var roadStatusPresenter: RoadStatusPresenter

    @Mock
    private lateinit var mView: RoadStatusContract.View


    @Before
    fun setup() {

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }

        MockitoAnnotations.initMocks(this)
        roadStatusPresenter = RoadStatusPresenter(roadStatusService)
        roadStatusPresenter.attachView(mView)
    }

    @Test
    fun `when api returns empty list then call show error with no list found`() {
        `when`(roadStatusService.getStatus("a1")).thenReturn(Single.just(Response.success(emptyList())))
        roadStatusPresenter.getStatus("a1")
        verify(mView).showError("No List found")

    }

    @Test
    fun `when api returns null call show error with no list found`() {
        `when`(roadStatusService.getStatus("a1")).thenReturn(
            Single.just(
                Response.success<List<RoadStatus>>(
                    null
                )
            )
        )
        roadStatusPresenter.getStatus("a1")
        verify(mView).showError("No List found")

    }

    @Test
    fun `when the api returns non empty list then call show result with data`() {
        val roadStatus = RoadStatus("", "a1", "", "", "", "", "", "")
        val statusList = listOf(roadStatus)

        `when`(roadStatusService.getStatus("a1")).thenReturn(Single.just(Response.success(statusList)))
        roadStatusPresenter.getStatus("a1")
        verify(mView).showResults(statusList)

    }

    @Test
    fun `when the api returns unknown error show error with unknow error message`() {
        `when`(roadStatusService.getStatus("a1")).thenReturn(
            Single.just(
                Response.error<List<RoadStatus>>(
                    404,
                    ResponseBody.create(MediaType.parse("application/json"), "")
                )
            )
        )
        roadStatusPresenter.getStatus("a1")
        verify(mView).showError("An unknown error happened: Unable to parse error body")

    }

    @Test
    fun `when the api returns error call show error with appropriate message`() {
        `when`(roadStatusService.getStatus("a1")).thenReturn(
            Single.just(
                Response.error<List<RoadStatus>>(
                    404,
                    ResponseBody.create(
                        MediaType.parse("application/json"), """
                {
    "type": "Tfl.Api.Presentation.Entities.ApiError, Tfl.Api.Presentation.Entities",
    "timestampUtc": "2020-03-03T13:44:05.8855288Z",
    "exceptionType": "EntityNotFoundException",
    "httpStatusCode": 404,
    "httpStatus": "NotFound",
    "relativeUri": "/road/a2sdf",
    "message": "The following road id is not recognised: a2sdf"
}
            """.trimIndent()
                    )
                )
            )
        )
        roadStatusPresenter.getStatus("a1")
        verify(mView).showError("The following road id is not recognised: a2sdf")

    }


    @Test
    fun `when api services returns an exception without message then show error with unknown error message`() {
        `when`(roadStatusService.getStatus("a1")).thenReturn(
            Single.error(
                RuntimeException()
            )
        )
        roadStatusPresenter.getStatus("a1")
        verify(mView).showError("An unknown error happened")

    }

    @Test
    fun `when api service returns an exception with a message then show error with the message`() {
        `when`(roadStatusService.getStatus("a1")).thenReturn(
            Single.error(
                RuntimeException("test error")
            )
        )
        roadStatusPresenter.getStatus("a1")
        verify(mView).showError("test error")

    }
}




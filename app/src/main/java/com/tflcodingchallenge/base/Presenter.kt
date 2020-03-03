package com.tflcodingchallenge.base

open class Presenter<T : BaseView> : BasePresenter<T> {
    var view: T? = null

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}
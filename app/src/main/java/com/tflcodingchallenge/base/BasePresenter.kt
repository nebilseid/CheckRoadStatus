package com.tflcodingchallenge.base

interface BasePresenter<T: BaseView> {
    fun attachView(view: T)
    fun detachView()
}
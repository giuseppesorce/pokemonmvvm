package com.giuseppesorce.architecture.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.giuseppesorce.architecture.CommonState
import com.giuseppesorce.architecture.ErrorState
import com.giuseppesorce.architecture.LoadingState


/**
 * @author Giuseppe Sorce
 */
abstract class BaseActivityViewModel<State : Any, Event : Any>(
) : AppCompatActivity() {


    abstract fun provideBaseViewModel(): BaseViewModel<State, Event>?
    abstract fun handleState(state: State)
    abstract fun handleEvent(event: Event)

    override fun onResume() {
        super.onResume()
        setupUI()
        provideBaseViewModel()?.stateHolder()?.observe(this, Observer { state -> handleState(state) })
        provideBaseViewModel()?.commonStateHolder()?.observe(this, Observer { state -> handleCommonState(state) })
        provideBaseViewModel()?.eventHolder()?.observe(this, Observer { event -> handleEvent(event) })
        initActivity()
    }

    open fun initActivity(){

    }
    open fun setupUI(){

    }

    private fun handleCommonState(commonState: CommonState) {
        handleLoading(commonState.loadingState)
        handleError(commonState.errorState)
    }


    open fun handleLoading(loadingState: LoadingState) {
        when (loadingState) {
            is LoadingState.Loading -> showLoadingState()
            is LoadingState.Idle -> showIdleState()
        }
    }


    open fun  showLoadingState() {
//        if (loadingDialog == null) loadingDialog = LoadingDialog(requireContext())
//        loadingDialog?.show()
    }

    open fun showIdleState() {
//        hideLoadingState()
//        errorDialog?.dismiss()
//        errorDialog = null
    }

    open fun handleError(errorState: ErrorState) {
//        when (errorState) {
//            is ErrorState.UnknownError ->
//            is ErrorState.Error ->
//        }
    }


}
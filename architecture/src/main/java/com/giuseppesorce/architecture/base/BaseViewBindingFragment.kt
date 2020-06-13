package com.giuseppesorce.architecture.base

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.giuseppesorce.architecture.CommonState
import com.giuseppesorce.architecture.ErrorState
import com.giuseppesorce.architecture.LoadingState
import com.giuseppesorce.architecture.SimpleAlertData


/**
 * @author Giuseppe Sorce
 */
abstract class BaseViewBindingFragment<State : Any, Event : Any>(
) : Fragment() {


    var viewFrag: View? = null
    private var fragmentInitialized = false
    abstract fun provideBaseViewModel(): BaseViewModel<State, Event>?
    abstract fun setupUI()
    abstract fun handleState(state: State)
    abstract fun handleEvent(event: Event)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (viewFrag == null) {
          setFragmentViewBinding(inflater, container)
        }
        return viewFrag
    }

    abstract fun setFragmentViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intitializeFragment()
    }

    private fun intitializeFragment() {
        setupUI()
        provideBaseViewModel()?.stateHolder()
            ?.observe(viewLifecycleOwner, Observer { state -> handleState(state) })
        provideBaseViewModel()?.commonStateHolder()
            ?.observe(viewLifecycleOwner, Observer { state -> handleCommonState(state) })
        provideBaseViewModel()?.eventHolder()
            ?.observe(viewLifecycleOwner, Observer { event -> handleEvent(event) })
        provideBaseViewModel()?.alertLiveData()
            ?.observe(viewLifecycleOwner, Observer { alert -> showDataAlert(alert) })
        if (!fragmentInitialized) {
            fragmentInitialized = true
            observerData()
            initFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewFrag= null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewFrag= null
    }

    private fun showDataAlert(alert: SimpleAlertData?) {
        alert?.let {
            var title = when (alert.titleRes) {
                -1 -> alert.title
                else -> getStr(alert.titleRes)
            }
            var message = when (alert.messageRes) {
                -1 -> alert.message
                else -> {
                    getStr(alert.messageRes)
                }
            }
            showAlert(title, message, alert.codeMessage)
        }
    }

    private fun getStr(res:Int):String{
        var resString =""
        try {
            resString= getString(res)
        }catch (ex:Exception){
        }
        return  resString
    }


    private fun handleCommonState(commonState: CommonState) {
        handleLoading(commonState.loadingState)
        handleError(commonState.errorState)
    }

    open fun showIdleState() {
        hideLoadingState()
//        errorDialog?.dismiss()
//        errorDialog = null
    }

    open fun handleLoading(loadingState: LoadingState) {
        when (loadingState) {
            is LoadingState.Loading -> showLoadingState()
            is LoadingState.Idle -> showIdleState()
        }
    }

    fun showAlert(title: String, messase: String, codeMessage: Int) {
        activity?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(title)
                .setMessage(messase)
                .setPositiveButton("Ok", object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int){
                        onClickPositive(codeMessage)
                    }
                })
                .show()
        }

    }

    open fun onClickPositive(codeMessage: Int) {


    }
    open fun initFragment() {

    }

    open fun observerData() {

    }

    open fun showLoadingState() {

    }

    open fun hideLoadingState() {
    }

    open fun handleError(errorState: ErrorState) {
        when (errorState) {
            is ErrorState.UnknownError -> displayUnknownError()
            is ErrorState.Error -> displayUnknownError()
        }
    }

    open fun displayUnknownError() {

    }

     fun setStatusBarColor(color:Int) {
        activity?.let { act->
            val window: Window = act.getWindow()
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            // finally change the color
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //    window.setStatusBarColor(Color.BLUE);
                window.setStatusBarColor(color

                )
            }
        }

    }


}
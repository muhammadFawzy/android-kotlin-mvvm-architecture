package mvvm.f4wzy.com.samplelogin.ui.login.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import mvvm.f4wzy.com.samplelogin.model.User
import mvvm.f4wzy.com.samplelogin.network.BackEndApi
import mvvm.f4wzy.com.samplelogin.network.WebServiceClient
import mvvm.f4wzy.com.samplelogin.util.SingleLiveEvent
import mvvm.f4wzy.com.samplelogin.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application), Callback<User> {


    var btnSelected: ObservableBoolean? = null
    var email: ObservableField<String>? = null
    var password: ObservableField<String>? = null
    var progressDialog: SingleLiveEvent<Boolean>? = null
    var userLogin: MutableLiveData<User>? = null

    init {
        btnSelected = ObservableBoolean(false)
        progressDialog = SingleLiveEvent<Boolean>()
        email = ObservableField("")
        password = ObservableField("")
        userLogin = MutableLiveData<User>()
    }

    fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(s.toString()) && password?.get()!!.length >= 8)


    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(email?.get()!!) && s.toString().length >= 8)


    }

    fun login() {
        progressDialog?.value = true
        WebServiceClient.client.create(BackEndApi::class.java).LOGIN(email = email?.get()!!
                , password = password?.get()!!)
                .enqueue(this)

    }

    override fun onResponse(call: Call<User>?, response: Response<User>?) {
        progressDialog?.value = false
        userLogin?.value = response?.body()

    }

    override fun onFailure(call: Call<User>?, t: Throwable?) {
        progressDialog?.value = false

    }

}
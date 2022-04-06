package com.example.cryptoappexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoappexample.api.CryptoAPI
import com.example.cryptoappexample.api.CryptoServices
import com.example.cryptoappexample.model.Crypto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {
    private val cryptoServices = CryptoServices()
    private val disposable = CompositeDisposable()

    val cryptos = MutableLiveData<List<Crypto>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()


    fun refreshData(){
        loading.value = true
        disposable.add(cryptoServices.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Crypto>>(){
                override fun onSuccess(t: List<Crypto>) {
                    showCrypto(t)
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    error.value = true
                }

            }))
    }

    private fun showCrypto(cryptoList: List<Crypto>){
        cryptos.value = cryptoList
        error.value = false
        loading.value = false
    }


    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}
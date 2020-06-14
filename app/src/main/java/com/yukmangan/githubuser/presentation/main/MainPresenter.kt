package com.yukmangan.githubuser.presentation.main

import com.yukmangan.githubuser.BuildConfig
import com.yukmangan.githubuser.network.github.GithubDatasource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter (private val view: MainView){

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getSearchData(q:String){

        view.showLoading()
        disposable.add(GithubDatasource.create()
            .getSearchData(BuildConfig.GITHUB_TOKEN,q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response->
                view.hideLoading()
                view.onSuccess(response.items)
            },{error->
                view.hideLoading()
                view.throwable(error)
            }))

    }
}
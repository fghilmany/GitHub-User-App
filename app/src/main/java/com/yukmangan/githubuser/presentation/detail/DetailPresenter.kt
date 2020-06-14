package com.yukmangan.githubuser.presentation.detail

import com.yukmangan.githubuser.BuildConfig
import com.yukmangan.githubuser.network.github.GithubDatasource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter (val view:DetailView){

    private val disposable : CompositeDisposable = CompositeDisposable()


    fun getDataDetail(username:String){

        disposable.add(GithubDatasource.create()
            .getDetail(BuildConfig.GITHUB_TOKEN,username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                view.onSuccess(response)
            },{error ->
                view.throwable(error)
            }))

    }

}
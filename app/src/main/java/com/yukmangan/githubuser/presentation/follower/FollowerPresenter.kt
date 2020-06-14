package com.yukmangan.githubuser.presentation.follower

import com.yukmangan.githubuser.BuildConfig
import com.yukmangan.githubuser.network.github.GithubDatasource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FollowerPresenter (val view:FollowerView){

    private val disposable : CompositeDisposable = CompositeDisposable()

    fun getFollower(username:String){

        view.showLoading()

        disposable.add(
            GithubDatasource.create()
                .getFollower(BuildConfig.GITHUB_TOKEN,username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response ->
                    view.onSuccess(response)
                    view.hideLoading()
                },{error ->
                    view.hideLoading()
                    view.throwable(error)
                }))

    }

}
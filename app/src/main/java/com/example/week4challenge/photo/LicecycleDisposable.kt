package com.example.week4challenge.photo

import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.disposables.Disposable

class LifecycleDisposable(
    private val disposable: Disposable
) : DefaultLifecycleObserver {

    override fun onDestroy(owner: LifecycleOwner) {
        disposable.dispose()
        super.onDestroy(owner)
    }
}
/*@MainThread
fun Disposable.autoDispose(
    lifecycle: LifecycleOwner
) = lifecycle.addObserver(LifecycleDisposable(this))
*/
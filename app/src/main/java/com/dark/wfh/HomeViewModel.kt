package com.dark.wfh
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

 sealed class UiState {
    object Loading : UiState()
    data class Success(val data: HomeData) : UiState()
    data class Error(val message: String) : UiState()
}

class HomeViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchData()
    }

    private fun fetchData() {
        _uiState.value = UiState.Loading
        compositeDisposable.add(
            repository.fetchHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ homeData ->
                    _uiState.value = UiState.Success(homeData)
                }, { error ->
                    _uiState.value = UiState.Error(error.message ?: "An unknown error occurred")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
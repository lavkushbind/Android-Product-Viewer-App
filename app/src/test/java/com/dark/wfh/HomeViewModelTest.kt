package com.dark.wfh

import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val repository: ProductRepository = mockk()

    @Before
    fun setUp() {
         RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }

        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `fetchData success should update uiState to Success`() {
         val mockData = HomeData(smartphones = listOf(), laptops = listOf())
        every { repository.fetchHomeData() } returns Single.just(mockData)


        assertTrue(viewModel.uiState.value is UiState.Success)
        assertEquals(mockData, (viewModel.uiState.value as UiState.Success).data)
    }

    @Test
    fun `fetchData error should update uiState to Error`() {
         val errorMessage = "Network Error"
        every { repository.fetchHomeData() } returns Single.error(RuntimeException(errorMessage))

        viewModel = HomeViewModel(repository)

         assertTrue(viewModel.uiState.value is UiState.Error)
        assertEquals(errorMessage, (viewModel.uiState.value as UiState.Error).message)
    }
}
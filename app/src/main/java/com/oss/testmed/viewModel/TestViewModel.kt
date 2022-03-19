package com.oss.testmed.viewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.testmed.model.Temp
import com.oss.testmed.retrofit.NetworkClient
import kotlinx.coroutines.launch
import java.util.HashMap

class TestViewModel : ViewModel() {

    var errorMessage = MutableLiveData("")
    var isLoading = MutableLiveData(false)
    var tempData = MutableLiveData<Temp>()

    fun getData(map: HashMap<String, String>) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val service = NetworkClient.getNetworkClient()
                val response = service.getData()
                if (response.isSuccessful) {
                    tempData.value = response.body()!!
                }
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage!!
            } finally {
                isLoading.value = false
            }
        }
    }

}
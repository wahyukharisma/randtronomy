package com.example.randtronomy.repository

import androidx.lifecycle.MutableLiveData
import com.example.randtronomy.services.model.AstronomyItem
import com.example.randtronomy.services.network.AstronomyServices
import com.example.randtronomy.util.ResultOfNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(
    private val astronomyServices: AstronomyServices
) {
    val data = MutableLiveData<ResultOfNetwork<ArrayList<AstronomyItem>>>()

    suspend fun getRandAstronomy(key: String){
        withContext(Dispatchers.IO){
            data.postValue(ResultOfNetwork.Success(
                astronomyServices.getRandom(key)
            ))
        }
    }
}
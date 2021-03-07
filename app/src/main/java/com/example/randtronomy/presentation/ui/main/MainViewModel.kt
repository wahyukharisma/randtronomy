package com.example.randtronomy.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randtronomy.repository.MainRepository
import com.example.randtronomy.services.model.AstronomyItem
import com.example.randtronomy.util.ResultOfNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: MainRepository,
    @Named("api_key") private val key: String
) : ViewModel(){
    val data : LiveData<ResultOfNetwork<ArrayList<AstronomyItem>>>

    init {
        this.data = repository.data
    }

    fun getRandomAstronomy(){
        viewModelScope.launch {
            try{
                repository.getRandAstronomy(key)
            }catch (throwable: Throwable){
                when(throwable){
                    is IOException -> repository.data
                        .postValue(ResultOfNetwork.Failure("[IO] error ${throwable.message} please retry", throwable))
                    is HttpException -> {
                        repository.data
                            .postValue(ResultOfNetwork.Failure("[HTTP] error ${throwable.message} please retry", throwable))
                    }
                    else -> repository.data
                        .postValue(ResultOfNetwork.Failure("[Unknown] error ${throwable.message} please retry", throwable))
                }
            }
        }
    }

}
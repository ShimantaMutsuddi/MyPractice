package com.mutsuddi.mypractice.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mutsuddi.mypractice.data.model.Characters
import com.mutsuddi.mypractice.data.remote.ApiInterface
import com.mutsuddi.mypractice.util.Resource
import javax.inject.Inject

class StarwarRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    private  val TAG = "StarwarRepository"

    //to hold quoteList
    private val _characters = MutableLiveData<Resource<List<Characters>>>()

    //accessable livedata
    val characters: LiveData<Resource<List<Characters>>>
        get() = _characters

    suspend fun getCharacters() {
        _characters.postValue(Resource.Loading())

        try {
            val result = apiInterface.getCharacters(1)
            Log.d(TAG, "getAllRepos: ${result?.body()}}")
            if (result.body() != null) {

                _characters.postValue(Resource.Success(result.body()!!.results))


            } else {
                _characters.postValue(Resource.Error("Network Failure"))
            }

        } catch (e: Exception) {
            _characters.postValue(Resource.Error(e.message.toString()))


        }


    }

}
package com.example.ecommerceapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.SliderModel
import com.google.firebase.database.*

class HomeViewModel : ViewModel() {
    private val firebase = FirebaseDatabase.getInstance()
    private val _banner = MutableLiveData<List<SliderModel>>()
    val banner: LiveData<List<SliderModel>> = _banner

    fun loadbanner() {
        val ref = firebase.getReference("Banner")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()

                for (snap in snapshot.children) {
                    try {
                        val data = snap.getValue(SliderModel::class.java)
                        data?.let { lists.add(it)
                            println("Loaded Image URL: ${it.url}")}
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error loading banner: ${error.message}")
            }
        })


    }
}

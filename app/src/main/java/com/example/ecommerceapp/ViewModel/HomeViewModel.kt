package com.example.ecommerceapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.CategoriesModel
import com.example.ecommerceapp.model.ItemModel
import com.example.ecommerceapp.model.SliderModel
import com.google.firebase.database.*

class HomeViewModel : ViewModel() {
    private val firebase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    val banner: LiveData<List<SliderModel>> = _banner
    private val _categories = MutableLiveData<List<CategoriesModel>>()
    val categories: LiveData<List<CategoriesModel>> = _categories
    private val _recommended = MutableLiveData<MutableList<ItemModel>>()
    val recommended: LiveData<MutableList<ItemModel>> = _recommended

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
    fun LoadCategories() {
        val ref = firebase.getReference("Category")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoriesModel>()

                for (snap in snapshot.children) {
                    try {
                        val data = snap.getValue(CategoriesModel::class.java)
                        if (data != null) {
                            lists.add(data)
                            println("Loaded Category: ${data.title}, Image URL: ${data.picUrl}")
                        } else {
                            println("Warning: Data is null for a category item.")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                _categories.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error loading categories: ${error.message}")
            }
        })
    }
    fun loadrecommended(){
        val ref = firebase.getReference("Items")
        val query : Query = ref.orderByChild("showRecommended").equalTo(true)
        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemModel>()
                for (snap in snapshot.children){
                    for (snap2 in snap.children){
                        val data = snap2.getValue(ItemModel::class.java)
                        if (data != null) {
                            lists.add(data)
                        }

                    }
                }
                _recommended.value = lists

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

}


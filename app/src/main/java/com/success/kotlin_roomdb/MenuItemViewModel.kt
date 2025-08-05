package com.success.kotlin_roomdb

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MenuItemViewModel (application: Application) : AndroidViewModel(application) {
    private val repository : MenuItemRepository
    val getItems: LiveData<List<MenuItem>>

    init{
        val menuItemDao = AppDatabase.getDatabase(application).MenuItemDao()
        repository = MenuItemRepository(menuItemDao)
        getItems = repository.getItems
    }

    fun insert(menuItem: MenuItem, context: Context) {
        viewModelScope.launch {
            val success = repository.insertWithCheck(menuItem, context)
            if (success) {
                Toast.makeText(context, "Complete Successfully.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun update(oldItem: MenuItem,newItem: MenuItem, context: Context)  {
        viewModelScope.launch {
            val success = repository.updateWithCheck(oldItem,newItem, context)
            if (success) {
                Toast.makeText(context, "Updated Successfully.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun delete(item: MenuItem) = viewModelScope.launch {
        repository.delete(item)
    }

}
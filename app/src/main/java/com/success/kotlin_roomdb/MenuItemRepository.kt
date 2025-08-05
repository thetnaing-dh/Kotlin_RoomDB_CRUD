package com.success.kotlin_roomdb

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.compareTo
import kotlin.text.insert

class MenuItemRepository (private val menuItemDao: MenuItemDao) {

    val getItems: LiveData<List<MenuItem>> = menuItemDao.getItems()

    suspend fun insertWithCheck(item: MenuItem, context: Context): Boolean {
        val count = menuItemDao.checkDuplicateName(item.name)
        return if (count > 0) {
            // Duplicate found, show alert
            withContext(Dispatchers.Main) {
                showDuplicateAlert(context)
            }
            false
        } else {
            // No duplicate, proceed with insert
            menuItemDao.insert(item)
            true
        }
    }

    suspend fun updateWithCheck(oldItem: MenuItem,newItem: MenuItem, context: Context) : Boolean{
        val count = menuItemDao.checkDuplicateUpdate(newItem.name,oldItem.id)
        return if (count > 0) {
            // Duplicate found, show alert
            withContext(Dispatchers.Main) {
                showDuplicateAlert(context)
            }
            false
        } else {
            // No duplicate, proceed with update
            menuItemDao.update(newItem)
            true
        }
    }

    private fun showDuplicateAlert(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Duplicate Item Name")
            .setMessage("An item with this name already exists!")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    suspend fun delete(item: MenuItem) {
        menuItemDao.delete(item)
    }
}
package com.success.kotlin_roomdb

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.success.kotlin_roomdb.databinding.ActivityMainBinding
import com.success.kotlin_roomdb.databinding.DialogAddBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var menuItemAdapter: MenuItemAdapter
    private lateinit var menuItemViewModel: MenuItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        menuItemViewModel = ViewModelProvider(this).get(MenuItemViewModel::class.java)

        // ------------------------- Edit / Delete Data ----------------------------
        menuItemAdapter = MenuItemAdapter(
            onEditClick = { menuItem ->
                showEditDialog(menuItem)
            },
            onDeleteClick = { menuItem ->
                AlertDialog.Builder(this)
                    .setTitle("Confirmation")
                    .setMessage("Are you sure to delete this Menu Item?")
                    .setPositiveButton("Yes") { _, _ -> menuItemViewModel.delete(menuItem)}
                    .setNegativeButton("No") { _, _ ->  }
                    .setCancelable(false)
                    .show()
            }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.menuItemAdapter
        }

        // ------------------------- Load Data ------------------------------------
        menuItemViewModel.getItems.observe(this) { menuItems ->
            menuItems?.let { menuItemAdapter.submitList(it) }
        }

        // ------------------------- Insert Data ----------------------------------
        binding.fab.setOnClickListener {
            val dialogBinding = DialogAddBinding.inflate(layoutInflater)
            val dialog = AlertDialog.Builder(this)
                .setView(dialogBinding.root)
                .create()

            dialogBinding.btnSave.setOnClickListener {
                val code = dialogBinding.edtCode.text.toString()
                val name = dialogBinding.edtName.text.toString()
                val saleprice = dialogBinding.edtSalePrice.text.toString().toDoubleOrNull() ?: 0.0
                val cost = dialogBinding.edtCost.text.toString().toDoubleOrNull() ?: 0.0
                val inactive = dialogBinding.cbInactive.isChecked

                if(code.isEmpty()){
                    Toast.makeText(this, "Please Fill Item Code.", Toast.LENGTH_SHORT).show()
                    dialogBinding.edtCode.requestFocus()
                }
                else{
                    if(name.isEmpty()){
                        Toast.makeText(this, "Please Fill Item Name.", Toast.LENGTH_SHORT).show()
                        dialogBinding.edtName.requestFocus()
                    }
                    else{
                        if(dialogBinding.btnSave.text == "Save"){
                            val newItem = MenuItem(code = code, name = name, saleprice = saleprice, cost = cost, inactive = inactive)
                            menuItemViewModel.insert(newItem,this)
                            dialogBinding.edtCode.text?.clear()
                            dialogBinding.edtName.text?.clear()
                            dialogBinding.edtSalePrice.text?.clear()
                            dialogBinding.edtCost.text?.clear()
                            dialogBinding.cbInactive.isChecked = false
                            dialogBinding.edtCode.requestFocus()
                        }
                    }
                }
            }

            dialogBinding.btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    private fun showEditDialog(menuItem: MenuItem){
        val dialogBinding = DialogAddBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()
        dialogBinding.tvTitle.setText("Edit Menu Item")
        dialogBinding.edtCode.setText(menuItem.code)
        dialogBinding.edtName.setText(menuItem.name)
        dialogBinding.edtSalePrice.setText(menuItem.saleprice.toInt().toString())
        dialogBinding.edtCost.setText(menuItem.cost.toInt().toString())
        dialogBinding.cbInactive.isChecked = menuItem.inactive
        dialogBinding.btnSave.text = "Update"

        dialogBinding.btnSave.setOnClickListener {
            val code = dialogBinding.edtCode.text.toString()
            val name = dialogBinding.edtName.text.toString()
            val saleprice = dialogBinding.edtSalePrice.text.toString().toDoubleOrNull() ?: 0.0
            val cost = dialogBinding.edtCost.text.toString().toDoubleOrNull() ?: 0.0
            val inactive = dialogBinding.cbInactive.isChecked

            if(code.isEmpty()){
                Toast.makeText(this, "Please Fill Item Code.", Toast.LENGTH_SHORT).show()
                dialogBinding.edtCode.requestFocus()
            }
            else{
                if(name.isEmpty()){
                    Toast.makeText(this, "Please Fill Item Name.", Toast.LENGTH_SHORT).show()
                    dialogBinding.edtName.requestFocus()
                }
                else{
                    val updateItem = MenuItem(id=menuItem.id,code = code, name = name, saleprice = saleprice, cost = cost, inactive = inactive)
                    menuItemViewModel.update(menuItem,updateItem,this)
                    dialog.dismiss()
                }
            }
        }
        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etNewItem: EditText
    private lateinit var btnAddItem: Button

    // Inisialisasi Adapter di sini
    private lateinit var adapter: ShoppingListAdapter

    // Inisialisasi DAO
    private lateinit var shoppingDao: ShoppingDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hubungkan komponen dari layout ke kode
        recyclerView = findViewById(R.id.rv_shopping_list)
        etNewItem = findViewById(R.id.et_new_item)
        btnAddItem = findViewById(R.id.btn_add_item)

        // Dapatkan instance DAO dari database
        shoppingDao = ShoppingDatabase.getDatabase(this).shoppingDao()

        // Siapkan RecyclerView
        setupRecyclerView()

        // Tambahkan aksi saat tombol "Tambah" diklik
        btnAddItem.setOnClickListener {
            val newItemName = etNewItem.text.toString()
            if (newItemName.isNotEmpty()) {
                val newItem = ShoppingItem(name = newItemName, quantity = 1)
                addItemToDatabase(newItem)
                etNewItem.text.clear()
            } else {
                Toast.makeText(this, "Nama barang tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        // Ambil data dari database dan perbarui adapter saat ada perubahan
        lifecycleScope.launch {
            shoppingDao.getAllItems().collect { shoppingItems ->
                withContext(Dispatchers.Main) {
                    if (!this@MainActivity::adapter.isInitialized) {
                        adapter = ShoppingListAdapter(shoppingItems.toMutableList()) { item ->
                            deleteItemFromDatabase(item)
                        }
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    } else {
                        adapter.updateList(shoppingItems)
                    }
                }
            }
        }
    }

    private fun addItemToDatabase(item: ShoppingItem) {
        // Jalankan operasi database di thread latar belakang
        lifecycleScope.launch(Dispatchers.IO) {
            shoppingDao.insertItem(item)
        }
    }

    private fun deleteItemFromDatabase(item: ShoppingItem) {
        // Jalankan operasi database di thread latar belakang
        lifecycleScope.launch(Dispatchers.IO) {
            shoppingDao.deleteItem(item)
        }
    }
}
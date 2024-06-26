package com.APPa2

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import com.APPa2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var editText: EditText
    private lateinit var buttonGuardar: Button
    private lateinit var buttonLimpiar: Button
    private lateinit var mainLayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        buttonGuardar = findViewById(R.id.buttonGuardar)
        buttonLimpiar = findViewById(R.id.buttonLimpiar)

        buttonGuardar.setOnClickListener {
            val texto = editText.text.toString()
            Toast.makeText(this, "Se guardo tu texto: $texto", Toast.LENGTH_SHORT).show()
        }

        buttonLimpiar.setOnClickListener {
            editText.text.clear()
        }
    }


}
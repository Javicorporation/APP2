package com.APPa2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings


class MainActivity : AppCompatActivity() {
    // creamos variable para la instancia de FirebaseRemoteConfig
    // para obtener los datos de la configuracion remota
    private lateinit var mFirebaseRemoteConfig: FirebaseRemoteConfig
    // creamos las variables grobales para el editText y los botones
    private lateinit var editText: EditText
    private lateinit var buttonGuardar: Button
    private lateinit var buttonLimpiar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inicializamos la instancia de FirebaseRemoteConfig
        mFirebaseRemoteConfig = Firebase.remoteConfig
        // configuramos las opciones de la configuración remota y
        // implementamos el intervalo de tiempo para que se actualice
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }

        //acplicaciones de la forma asincronica
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        // se establece los valores por defecto de la configuración remota
        // este archivo se crea en la carpeta res/xml,
        // por defecto debemos crear primero el archivo
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        // llamamos a la funcion para obtener los datos de la configuracion remota
        fetchRemoteConfig()

        // inicializamos las variables globales para el editText y los botones
        editText = findViewById(R.id.editText)
        buttonGuardar = findViewById(R.id.buttonGuardar)
        buttonLimpiar = findViewById(R.id.buttonLimpiar)

        // definimos el comportamiento de los botones guardar y limpiar
        buttonGuardar.setOnClickListener {
            val texto = editText.text.toString()
            Toast.makeText(this, "Se guardo tu texto: $texto", Toast.LENGTH_SHORT).show()
        }

        buttonLimpiar.setOnClickListener {
            editText.text.clear()
        }
    }

    private fun fetchRemoteConfig() {
        // sirve para las configuraciones remotas desde Firebase y las aplica.
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
            // utilizamos una condicional para comprobar si la tarea se realizó correctamente
            if (task.isSuccessful) {
                    val updated = task.result
                    Toast.makeText(this, "Configuración actualizada: $updated", Toast.LENGTH_SHORT)
                        .show()
                    applyRemoteConfig()
                } else {
                    Toast.makeText(this, "Error al actualizar la configuración", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    // funcion para obtener y aplicar los datos de la configuracion remota
    private fun applyRemoteConfig() {
        val mensajeEditTexto = mFirebaseRemoteConfig.getString("cambiarTexto")
        val mostrarBoton = mFirebaseRemoteConfig.getBoolean("mostrarBoton")
        // aplicamos los datos obtenidos a las variables globales
        editText.hint = mensajeEditTexto
        buttonLimpiar.visibility = if (mostrarBoton) View.VISIBLE else View.GONE
    }
}














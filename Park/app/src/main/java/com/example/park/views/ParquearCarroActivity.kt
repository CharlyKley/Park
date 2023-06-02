@file:Suppress("DEPRECATION")

package com.example.park.views

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.park.databinding.ActivityParquearCarroBinding
import com.example.park.models.ParquearCarroModelo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class ParquearCarroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParquearCarroBinding
    private lateinit var fstore: FirebaseFirestore
    private lateinit var fauth: FirebaseAuth
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParquearCarroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fauth=FirebaseAuth.getInstance()
        fstore=FirebaseFirestore.getInstance()

        dialog = ProgressDialog(this)
        dialog.setTitle("Actualizando")
        dialog.setMessage("Guardando datos")


        binding.parqueaderoBoton.setOnClickListener { view ->
            val carro_nombre = binding.parqueaderoNombre.text.toString()
            val carro_numero = binding.parqueaderoNumero.text.toString()
            val carro_placa = binding.parqueaderoPlaca.text.toString()
            val carro_tipo_vehiculo = binding.parqueaderoTipo.text.toString()
            val carro_tarifa = binding.parqueaderoTarifa.text.toString()
            val carro_tiempo = Calendar.getInstance().timeInMillis
            val carro_id: String = UUID.randomUUID().toString()

            val parquearCarroModelo = ParquearCarroModelo()
                .apply {

                setId_Carro(carro_id)
                setPlaca_Carro(carro_placa)
                setNombre_Carro(carro_nombre)
                setNumero_Carro(carro_numero)
                setTipo_Vehiculo_Carro(carro_tipo_vehiculo)
                setTiempo_Carro(carro_tiempo)
                setTarifa_Carro(carro_tarifa)
                setUserId_Carro(fauth.currentUser?.uid)
                setStatus_Carro("Pendiente")
            }

            dialog.setTitle("Actualizando")
            dialog.setMessage("Guardando datos")
            dialog.show()

            val collectionPath = "Parqueando"
            val documentRef = fstore.collection(collectionPath).document(carro_id)
            documentRef.set(parquearCarroModelo)
                .addOnSuccessListener {
                    dialog.cancel()
                    finish()
                }
                .addOnFailureListener { e ->
                    dialog.cancel()
                    Toast.makeText(this@ParquearCarroActivity, e.message, Toast.LENGTH_SHORT).show()
                }


        }
    }
}
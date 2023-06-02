@file:Suppress("DEPRECATION")

package com.example.park.views
import com.example.park.viewmodels.fireBase
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.park.databinding.ActivityMainBinding
import com.example.park.models.AdaptadorCarros
import com.example.park.models.ParquearCarroModelo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(),
    AdaptadorCarrosInterface {
    private lateinit var binding: ActivityMainBinding

    private lateinit var fauth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore

    private lateinit var adaptador: AdaptadorCarros

    var pagado = 0
    var pendiente = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        //initializer firebase auth
        fireBase.iniciarFirebaseAuth()
        fireBase.iniciarFirebaseFirestore()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fauth = Firebase.auth
        fauth = FirebaseAuth.getInstance()
        fstore = FirebaseFirestore.getInstance()
        adaptador = AdaptadorCarros(this, this)


        binding.parqueaderoReciclador.adapter = adaptador
        binding.parqueaderoReciclador.layoutManager= LinearLayoutManager(this)
        //binding.parqueaderoReciclador.setAdapter(adaptador)
        //binding.parqueaderoReciclador.setLayoutManager(LinearLayoutManager(this))



        binding.parquearboton.setOnClickListener {
            startActivity(Intent(it.context, ParquearCarroActivity::class.java))
        }

    }

  override fun onStart() {
      super.onStart()
      val progressDialog = ProgressDialog(this)
      progressDialog.setTitle("Esperando Datos")
      progressDialog.setMessage("Procesando")

      if (fauth.currentUser == null) {
          progressDialog.show()
          fauth.signInAnonymously()
              .addOnSuccessListener {
                  progressDialog.dismiss()
              }
              .addOnFailureListener { e ->
                  progressDialog.dismiss()
                  Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
              }
      } else {
          progressDialog.dismiss()
      }
  }
    override fun onResume() {
        super.onResume()
        loadData()
    }
    private fun loadData() {
        fstore.collection("Parqueando")
            .whereEqualTo("userId_Carro", fauth.currentUser?.uid)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                pagado = 0
                pendiente = 0
                val dsList = queryDocumentSnapshots.documents
                adaptador.clear()
                for (documentSnapshot in dsList) {
                    val model = documentSnapshot.toObject(ParquearCarroModelo::class.java)
                    if (model != null) {
                        if (model.status_Carro == "Pendiente") {
                            val tarifa = model.tarifa_Carro
                            pendiente += tarifa.toInt()
                        }
                        if (model.status_Carro == "Pagado") {
                            val tarifa = model.tarifa_Carro
                            pagado += tarifa.toInt()
                        }
                    }
                    if(model != null) {
                       adaptador.add(model)
                   }
                }
                binding.parqueaderoPagado.text = pagado.toString()
                binding.parqueaderoPendiente.text = pendiente.toString()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
            }
    }


   override fun OnLongClick(pos: Int, id_carro: String?) {
       val builder = AlertDialog.Builder(this)
       builder.setTitle("status")
           .setPositiveButton("Pagado") { _, _ ->
               if (id_carro != null) {
                   changeStatus("Pagado",id_carro)
               }
           }
           .setNegativeButton("Cancelado") { _, _ ->
               if (id_carro != null) {
                   changeStatus("Cancelado",id_carro)
               }
           }
       builder.show()
   }

    private fun changeStatus(status_Carro: String, id: String) {
        loadData()
        fstore.collection("Parqueando")
            .document(id)
            .update("status_Carro",status_Carro)
            .addOnSuccessListener {
                Toast.makeText(this@MainActivity, "Status Update", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
            }

    }



}


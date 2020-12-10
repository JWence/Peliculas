package com.example.peliculas

import PeliculasItems.ItemData
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import java.text.SimpleDateFormat
import java.util.*

class DetallesPeliculas : AppCompatActivity() {

    var todoDbController:Db.Controller? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        vBtnComentar.setOnClickListener{

            var intent = Intent(this, Comentar::class.java)
            startActivity(intent)
        }
        todoDbController = Db.Controller(this)
        /* Back button support */
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        /* formulario */
        formSetUp()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    fun formSetUp() {
        when(intent.getStringExtra("TYPE")) {
            "ADD" -> formSetUpAdd()
            "EDIT" -> formSetUpEdit()
        }
        vTodoInputDate.setOnClickListener {
            showDatePicker()
        }
        vTodoInputImage.setOnFocusChangeListener { view, isFocus ->
            val uri = vTodoInputImage.text.toString().trim()
            if(!isFocus && uri.isNotEmpty()) {
                updateImage(uri)
            }
        }
    }
    fun formSetUpAdd(){
        setTitle("Detalles")
        vBtnComentar.setOnClickListener {
            // Validaciones

            var titleAux = vTodoInputTitle.text.toString()
            var messageAux = vTodoInputMessage.text.toString()
            var dateAux = vTodoInputDate.text.toString()
            var imageaux = vTodoInputImage.text.toString()

            todoDbController!!.insert(
                ItemData( "1", titleAux, messageAux,dateAux, imageaux)
            )
            setResult(Activity.RESULT_OK, intent)
            onBackPressed()
        }
    }
    fun formSetUpEdit(){
        setTitle("Detalles")

        var idAux = intent.getStringExtra("ID")
        vTodoInputTitle.setText(intent.getStringExtra("TITULO"))
        vTodoInputMessage.setText(intent.getStringExtra("SINOPSIS"))
        vTodoInputDate.setText(intent.getStringExtra("ESTRENO"))
        intent.getStringExtra("PORTADA")?.let {
            vTodoInputImage.setText(it)
            updateImage(it)
        }
        vBtnComentar.setOnClickListener {
            // Validaciones

            var titleAux = vTodoInputTitle.text.toString()
            var messageAux = vTodoInputMessage.text.toString()
            var dateAux = vTodoInputDate.text.toString()
            var imageaux = vTodoInputImage.text.toString()
            var ide = idAux

            println(ide)
            todoDbController!!.update(
                ItemData( ide, titleAux, messageAux,dateAux, imageaux)
            )
            setResult(Activity.RESULT_OK, intent)
            onBackPressed()
        }

    }
    fun showDatePicker() {
        var formatDate = SimpleDateFormat("dd/MM/YYYY", Locale.US)
        val getDate = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            val selectDate = Calendar.getInstance()
            selectDate.set(Calendar.YEAR, year)
            selectDate.set(Calendar.MONTH, month)
            selectDate.set(Calendar.DAY_OF_MONTH, day)
            val date = formatDate.format(selectDate.time)
            vTodoInputDate.setText(date)
        }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }
    fun updateImage(uri:String) {
        Picasso.get().load(uri).into(vImagePreview)
    }
}

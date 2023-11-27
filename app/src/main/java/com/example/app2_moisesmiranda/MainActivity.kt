package com.example.app2_moisesmiranda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.util.Date

class MainActivity : AppCompatActivity() {

    val numeros = Numeros()
    var calculo = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnCalcular = findViewById<android.widget.Button>(R.id.calcular)

        // Inflar la toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu)
        setSupportActionBar(toolbar)

        val textoEditable = findViewById<EditText>(R.id.numeroIntent)

        // Comprobar si ha llegado un intent o si se usara el numero por defecto
        if (intent.getStringExtra(Intent.EXTRA_TEXT) == null) {
            textoEditable.text = Editable.Factory.getInstance().newEditable("220")
        }else{
            val textoIntent = intent.getStringExtra(Intent.EXTRA_TEXT)
            textoEditable.text = Editable.Factory.getInstance().newEditable(textoIntent)
        }

        val spinner: Spinner = findViewById(R.id.spinner1)
        val spinner2: Spinner = findViewById(R.id.spinner2)
        val spinner3: Spinner = findViewById(R.id.spinner3)
        val spinner4: Spinner = findViewById(R.id.spinner4)

        // Le cargo numeros del 0 al 9 a los spinners
        ArrayAdapter.createFromResource(
            this,
            R.array.numeros,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner2.adapter = adapter
            spinner3.adapter = adapter
            spinner4.adapter = adapter
        }


        // Creo las opciones del listview
        val opciones = listOf(
            Opcion(resources.getString(R.string.suma), R.drawable.suma),
            Opcion(resources.getString(R.string.numero_amigos), R.drawable.numerosamigos)
        )

        // Cargo el adaptador personalizado para ver la imagen y el texto
        val listViewOpciones = findViewById<ListView>(R.id.operacion)
        val adapter = ListViewAdapter(this, R.layout.opciones, opciones)
        listViewOpciones.adapter = adapter

        // Creo el listener para aplicar cada opcion segun cual se elija
        listViewOpciones.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val opcion = opciones[position]
                calculo = opcion.nombre == resources.getString(R.string.suma)
            }

        // Creo el listener para el boton de calcular
        btnCalcular.setOnClickListener {

            // Crea el numero con el spinner
            calcularNumeroConSpinners()

            var imgCalculo = findViewById<ImageView>(R.id.imgCalculo)
            var numeroIntent = findViewById<EditText>(R.id.numeroIntent)
            var segundoNumero = findViewById<EditText>(R.id.segundoNumero)
            var resultado = findViewById<TextView>(R.id.resultado)

            if (calculo) {
                imgCalculo.setImageResource(R.drawable.suma)
                resultado.text =
                    numeros.sumarNumeros(numeroIntent.text.toString().toInt(),segundoNumero.text.toString().toInt())
                        .toString()
            }
            else{
                imgCalculo.setImageResource(R.drawable.numerosamigos)
                if (numeros.calcularNumerosAmigos(numeroIntent.text.toString().toInt(),segundoNumero.text.toString().toInt())){
                    resultado.text = "Son números amigos"
                }else{
                    resultado.text = "No son números amigos"
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.fecha -> {
                val toast = Toast.makeText(this, Date().toString(), Toast.LENGTH_SHORT)
                toast.show()
            }
            R.id.acerca -> {
                val toast = Toast.makeText(this, "El desarrollador es Moisés Miranda", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun calcularNumeroConSpinners(){
        val spinner: Spinner = findViewById(R.id.spinner1)
        val spinner2: Spinner = findViewById(R.id.spinner2)
        val spinner3: Spinner = findViewById(R.id.spinner3)
        val spinner4: Spinner = findViewById(R.id.spinner4)

        val numero1 = spinner.selectedItem.toString().toInt()
        val numero2 = spinner2.selectedItem.toString().toInt()
        val numero3 = spinner3.selectedItem.toString().toInt()
        val numero4 = spinner4.selectedItem.toString().toInt()

        val segundoNumero = findViewById<EditText>(R.id.segundoNumero)

        val resultado = numero1.toString() + numero2.toString() + numero3.toString() + numero4.toString()

        val textoEditable = findViewById<EditText>(R.id.numeroIntent)
        segundoNumero.text = Editable.Factory.getInstance().newEditable(resultado.toString())


    }

}




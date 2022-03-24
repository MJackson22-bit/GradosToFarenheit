package com.example.kpractice1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import com.example.kpractice1.databinding.ActivityMainBinding
import org.xml.sax.Parser
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate as validate

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private var celcius: Double = 0.0
    private var farenheit: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnConvertir.setOnClickListener {
            converter()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun converter() {
        if (validate()) {
            if (binding.rbFarenheitCelcius.isChecked) {
                farenheit = binding.etCantidad.text.toString().toDouble()
                celcius = (farenheit - 32) * 5 / 9
                binding.tvResultado.text = "$farenheit grados farenheit son $celcius grados celcius"
            }else if(binding.rbCelciusFarenheit.isChecked){
                celcius = binding.etCantidad.text.toString().toDouble()
                farenheit = (celcius * 9/5) + 32
                binding.tvResultado.text = "$celcius grados celcius son $farenheit grados farenheit"
            }
        }else{
            Toast.makeText(this, "Ingrese la cantida", Toast.LENGTH_SHORT).show()
        }
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun validate(): Boolean =  binding.etCantidad.text.toString().isNotEmpty()

    override fun onQueryTextSubmit(query: String?): Boolean {
        converter()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = false
}
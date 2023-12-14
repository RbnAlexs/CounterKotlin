package com.example.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    /*viewBinding = true es una configuración que se utiliza en el archivo build.gradle (app) de un
    módulo de la aplicación para habilitar el enlace de vistas (view binding) en ese módulo. Esto
    permite manejar la siguiente variable binding.
     */

    private lateinit var binding: ActivityMainBinding

    private  var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCount ()

        // Click normal
        binding.btnSum.setOnClickListener{
            count += 1
            setCount()
        }

        //Click largo
        binding.btnSum.setOnLongClickListener {
            count = 0
            setCount()
            true
        }

    }

    private fun setCount() {
        binding.tvCount.text = count.toString()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.run{
            putInt(Companion.PARAM_COUNT, count)
        }
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            putInt(Companion.PARAM_COUNT, count)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        count = savedInstanceState.getInt(PARAM_COUNT)
        setCount()
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        private const val PARAM_COUNT: String = "param_count"
    }
}
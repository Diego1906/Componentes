package com.teste.componentes

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.buttonToastMe -> {
                val toast = Toast.makeText(this, "Toast Notifications!", Toast.LENGTH_LONG)

                // Buscando o layout Padrão da toast
                //toast.view.findViewById<TextView>(android.R.id.message).setTextColor(Color.GREEN)

                val toastLayout = layoutInflater.inflate(R.layout.toast_custom, null)
                toast.view = toastLayout

                val textViewLayout = toast.view.findViewById<TextView>(R.id.textMessage)
                textViewLayout.setTextColor(Color.RED)
                textViewLayout.text = "Toast Notifications!"

                toast.show()
            }
        }
    }

    private fun setListeners() {
        buttonToastMe.setOnClickListener(this)
    }
}

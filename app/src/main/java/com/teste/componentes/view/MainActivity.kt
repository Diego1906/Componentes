package com.teste.componentes.view

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.teste.componentes.R
import com.teste.componentes.mock.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var progress: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()

        loadSpinner()

        progress = ProgressDialog(this)
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
                textViewLayout.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                textViewLayout.text = "Toast Notifications!"

                toast.show()
            }
            R.id.buttonSnackMe -> {
                val snackbar = Snackbar.make(constraintLayout, "Snackbar Notification!", Snackbar.LENGTH_LONG)

                // Cor do texto
                snackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
                    .setTextColor(ContextCompat.getColor(this, R.color.colorOrange))

                // Cor do background
                //snackbar.view.setBackgroundColor(Color.BLUE)

                // Para pegar a cor do arquivo de colors usar ( ContextCompat.getColor(this, R.color.colorBlue) )
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue))

                // Mostrar imp da action
                snackbar.setAction("Desfazer", {
                    Snackbar.make(constraintLayout, "Ação desfeita", Snackbar.LENGTH_SHORT).show()
                })

                // Mudar cor do texto da action
                snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.colorPurple))

                snackbar.show()
            }
            R.id.buttonGetSpinner -> {
                // val value = spinnerDynamic.selectedItem.toString()
                val value = spinnerDynamic.selectedItemPosition.toString()
                Toast.makeText(this, value, Toast.LENGTH_LONG).show()
            }
            R.id.buttonSetSpinner -> {
                spinnerDynamic.setSelection(3)
            }
            R.id.buttonProgressDialog -> {
                progress.setTitle("Título")
                progress.setMessage("Mensagem")
                progress.show()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        val value: String = parent.getItemAtPosition(position).toString()
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()
    }

    private fun setListeners() {
        buttonToastMe.setOnClickListener(this)
        buttonSnackMe.setOnClickListener(this)
        buttonGetSpinner.setOnClickListener(this)
        buttonSetSpinner.setOnClickListener(this)
        buttonProgressDialog.setOnClickListener(this)

        spinnerDynamic.onItemSelectedListener = this
    }

    private fun loadSpinner() {
        val list = Mock.getList()

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)

        spinnerDynamic.adapter = adapter
    }
}

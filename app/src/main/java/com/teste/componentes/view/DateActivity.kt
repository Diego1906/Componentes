package com.teste.componentes.view

import android.app.DatePickerDialog
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.teste.componentes.R
import kotlinx.android.synthetic.main.activity_date.*
import java.text.SimpleDateFormat
import java.util.*

class DateActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePicker.OnTimeChangedListener {

    private val mSimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        setListeners()
    }

    // Evento da View OnclickListener
    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.buttonDatePicker -> {
                openDatePickerDialog()
            }
            R.id.buttonGetTimePicker -> {
                var value: Any = ""

                if (Build.VERSION.SDK_INT >= 23) {
                    value = "${timePicker.hour}:${timePicker.minute}"
                } else {
                    value = "${timePicker.currentHour}:${timePicker.currentMinute}"
                }

                Toast.makeText(this, value.toString(), Toast.LENGTH_SHORT).show()
            }
            R.id.buttonSetTimePicker -> {
                if (Build.VERSION.SDK_INT >= 23) {

                    timePicker.apply {
                        hour = 20
                        minute = 15
                    }
                } else {
                    timePicker.apply {
                        currentHour = 20
                        currentMinute = 15
                    }
                }
            }
        }
    }

    // Evento de alteração de data no componente DatePicker
    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        val value = mSimpleDateFormat.format(calendar.time)
        textDataPicker.text = value
    }

    // Evento de mudança no TimePicker
    override fun onTimeChanged(view: TimePicker, hourOfDay: Int, minute: Int) {
        Toast.makeText(
            this,
            "$hourOfDay:" + if (minute > 9) minute else "0$minute",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setListeners() {
        buttonDatePicker.setOnClickListener(this)
        buttonGetTimePicker.setOnClickListener(this)
        buttonSetTimePicker.setOnClickListener(this)

        timePicker.setOnTimeChangedListener(this)
    }

    private fun openDatePickerDialog() {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, this, year, month, day).show()
    }
}

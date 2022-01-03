package com.soya.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        //Click Listener Example
        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }

    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "${selectedMonth + 1}/${selectedDayOfMonth}/$selectedYear"

                tvSelectedDate?.text = selectedDate

                this.ageCalculatorInMinutes(selectedDate)


            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()


    }

    private fun ageCalculatorInMinutes(selectedDate: String) {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val theDate = sdf.parse(selectedDate)

        theDate?.let {

            val selectedDateInMinutes = theDate.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time / 60000
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            tvAgeInMinutes?.text = differenceInMinutes.toString()

        }


    }
}
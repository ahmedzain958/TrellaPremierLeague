package com.zainco.trellapremierleague

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun FilterWithDatePicker(title: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf("") }
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
            onDateSelected(selectedDate)
        },
        year,
        month,
        day
    )

    OutlinedTextField(
        value = selectedDate,
        onValueChange = { selectedDate = it },
        label = { Text(title) },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "Open Date Picker"
                )
            }
        }
    )
}
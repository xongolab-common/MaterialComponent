package com.example.materialcomponent

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.materialcomponent.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // TODO : Material Button
        // Widget.MaterialComponents.Button.TextButton -	Text button (no background)
        // Widget.MaterialComponents.Button.OutlinedButton -  Outlined button
        // Widget.MaterialComponents.Button -	Default filled button
        // Widget.MaterialComponents.Button.Icon -	Button with an icon

        // TODO : MaterialCardView
        // cardElevation - Adds shadow effect
        // strokeColor - Sets the border color.
        // strokeWidth - Defines the border thickness
        // cardUseCompatPadding - Ensures proper shadow padding.
        // cardPreventCornerOverlap - Prevents content from overlapping rounded corners.

        // TODO : ChipGroup
        // app:singleSelection="true" → Only one chip can be selected at a time.
        // app:selectionRequired="true" → At least one chip must be selected.


        binding.apply {
            chipExample.setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Chip clicked: ${binding.chipExample.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Handling Chip Selection in ChipGroup
            chipGroup.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId != -1) { // Ensure a chip is selected
                    val selectedChip: Chip? = group.findViewById(checkedId) // Get selected chip
                    selectedChip?.let {
                        val chipText = it.text.toString()
                        tvSelectedChip.text = "Selected Chip: $chipText"
                        Toast.makeText(this@MainActivity, "Selected: $chipText", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    tvSelectedChip.text = "Selected Chip: None" // Reset if no chip is selected
                }
            }


            // 5. Dynamically Adding Chips in Kotlin

            // 6. Implementing a Closeable Chip

            // TODO : TextInputLayout
            // TextInputLayout wraps TextInputEditText for better styling and validation.
            // app:endIconMode="clear_text" adds a clear button.
            // app:helperText="Use a valid email address" provides a hint below the input field.

            // TODO : autoCompleteTextView
            val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")

            val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_dropdown_item_1line, items)
            autoCompleteTextView.setAdapter(adapter)

            // Optional: Handle item selection
            autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                println("Selected: $selectedItem")
            }


            // TODO : MaterialCheckBox

            materialCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    Toast.makeText(this@MainActivity, "Checkbox checked", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Checkbox unchecked", Toast.LENGTH_SHORT).show()
                }
            }

            // TODO : MaterialSwitch
            toggleButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    Toast.makeText(this@MainActivity, "Switch is ON", Toast.LENGTH_SHORT).show()
                    // toggleButton.setBackgroundResource(R.drawable.ic_switch_on_bg)
                } else {
                    Toast.makeText(this@MainActivity, "Switch is OFF", Toast.LENGTH_SHORT).show()
                    // toggleButton.setBackgroundResource(R.drawable.ic_switch_off_bg)
                }
            }

            // TODO : MaterialRadioButton
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedRadioButton = findViewById<RadioButton>(checkedId)
                Toast.makeText(this@MainActivity, "Selected: ${selectedRadioButton.text}", Toast.LENGTH_SHORT).show()
            }


            // TODO : MaterialDatePicker
            btnPickDate.setOnClickListener {
                val datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

                datePicker.show(supportFragmentManager, "DATE_PICKER")

                datePicker.addOnPositiveButtonClickListener { selectedDate ->
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val formattedDate = sdf.format(Date(selectedDate))
                    tvSelectedDate.text = "Selected Date: $formattedDate"
                }
            }

            // TODO : MaterialTimePicker
            btnPickTime.setOnClickListener {
                val timePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H) // 12-hour format
                    .setHour(10) // Default hour
                    .setMinute(30) // Default minute
                    .setTitleText("Select Time")
                    .build()

                timePicker.show(supportFragmentManager, "MATERIAL_TIME_PICKER")

                timePicker.addOnPositiveButtonClickListener {
                    val selectedHour = timePicker.hour
                    val selectedMinute = timePicker.minute
                    val formattedTime = String.format(
                        "%02d:%02d %s",
                        if (selectedHour > 12) selectedHour - 12 else selectedHour,
                        selectedMinute,
                        if (selectedHour >= 12) "PM" else "AM"
                    )
                    tvSelectedTime.text = "Selected Time: $formattedTime"
                }
            }
        }
    }

}
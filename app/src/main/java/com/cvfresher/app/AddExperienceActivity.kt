package com.cvfresher.app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Calendar

class AddExperienceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_experience)

        val chkCurrent = findViewById<CheckBox>(R.id.chkCurrent)
        val lytEndYear = findViewById<View>(R.id.lytEndYear)
        val txtEmployer = findViewById<EditText>(R.id.txtEmployer)
        val txtJobTitle = findViewById<EditText>(R.id.txtJobTitle)
        val txtDescription = findViewById<EditText>(R.id.txtDescription)
        val txtEndYear = findViewById<EditText>(R.id.txtEndYear)
        val txtStartYear = findViewById<EditText>(R.id.txtStartYear)
        val btnAddExp = findViewById<Button>(R.id.btnAddExp)
        val calendar = Calendar.getInstance()

        fun notAllSpaces(string: String): Boolean {
            //This regex checks the string for at least one non-space character
            val regex = Regex(".*\\S.*")
            val cob = regex.matches(string)
            return cob
        }

        //these boolean variables record if the fields have been changed (C)
        var employerC = false
        var jobTitleC = false
        var referenceC = false
        var endYearC = false
        var startYearC = false

        chkCurrent.setOnClickListener {
            if (chkCurrent.isChecked) {
                txtEndYear.setText("0")
                txtEndYear.text.clear()
                lytEndYear.visibility = View.INVISIBLE
            } else {
                lytEndYear.visibility = View.VISIBLE
                endYearC = false
            }
        }
        //Employer Error Handling
        txtEmployer.doOnTextChanged { text, _, _, _ ->
            employerC = true
            val l = text!!.length
            if (text.isNotEmpty()) {
                if (notAllSpaces(text.toString())) {
                    if (l > 30) {
                        txtEmployer.error = "Max 30 Characters*"
                    } else {
                        txtEmployer.error = null
                    }
                } else {
                    txtEmployer.error = "Must include characters*"
                }
            } else {
                txtEmployer.error = "Required*"
            }
        }

        txtJobTitle.doOnTextChanged { text, _, _, _ ->
            jobTitleC = true
            val l = text!!.length
            if (text.isNotEmpty()) {
                if (notAllSpaces(text.toString())) {
                    if (l > 30) {
                        txtJobTitle.error = "Max 30 Characters*"
                    } else {
                        txtJobTitle.error = null
                    }
                } else {
                    txtJobTitle.error = "Must include characters*"
                }
            } else {
                txtJobTitle.error = "Required*"
            }
        }

        txtDescription.doOnTextChanged { text, _, _, _ ->
            referenceC = true
            val l = text!!.length
            if (text.isNotEmpty()) {
                if (notAllSpaces(text.toString())) {
                    if (l > 250) {
                        txtDescription.error = "Max 250 Characters*"
                    } else {
                        txtDescription.error = null
                    }
                }else{
                    txtDescription.error = "Must include characters*"
                }
            } else {
                txtDescription.error = "Required*"
            }
        }

        txtStartYear.doOnTextChanged { text, _, _, _ ->
            startYearC = true
            val l = text!!.length
            if (text.isNotEmpty()) {
                val value: Int = Integer.parseInt(text.toString())
                if (l != 4 || value < calendar.get(Calendar.YEAR) - 100 || value > calendar.get(
                        Calendar.YEAR
                    )
                ) {
                    txtStartYear.error = "Invalid Year*"
                } else {
                    txtStartYear.error = null
                }
            } else {
                txtStartYear.error = "Required*"
            }
        }

        txtEndYear.doOnTextChanged { text, _, _, _ ->
            endYearC = true
            val l = text!!.length
            if (text.isNotEmpty()) {
                val value: Int = Integer.parseInt(text.toString())
                if (l != 4 || value < calendar.get(Calendar.YEAR) - 100 || value > calendar.get(
                        Calendar.YEAR
                    )
                ) {
                    txtEndYear.error = "Invalid Year*"
                } else {
                    txtEndYear.error = null
                }
            } else { //text is empty
                if (chkCurrent.isChecked) { //text not required when chkCurrent is checked
                    txtEndYear.error = null
                } else { //text required
                    txtEndYear.error = "Required*"
                }

            }
        }

        btnAddExp.setOnClickListener {
            //Checks if any fields went un-edited
            if (employerC && jobTitleC && referenceC && endYearC && startYearC) {
                //Checks for any errors during submission
                if (!txtStartYear.error.isNullOrEmpty()
                    || !txtEndYear.error.isNullOrEmpty()
                    || !txtEmployer.error.isNullOrEmpty()
                    || !txtJobTitle.error.isNullOrEmpty()
                    || !txtJobTitle.error.isNullOrEmpty()
                    || !txtDescription.error.isNullOrEmpty()
                ) {
                    Toast.makeText(
                        this@AddExperienceActivity,
                        "Make sure all fields are filled correctly",
                        Toast.LENGTH_SHORT
                    ).show()
                } else { //SUBMISSION
                    Toast.makeText(
                        this@AddExperienceActivity,
                        "Submitting",
                        Toast.LENGTH_SHORT
                    ).show()
                    //DO SUBMISSION HERE
                    val employer = txtEmployer.text.toString().trim()
                    val jobTitle = txtJobTitle.text.toString().trim()
                    val description = txtDescription.text.toString().trim()
                    val startYear = Integer.parseInt(txtStartYear.text.toString())
                    val isCurrent = chkCurrent.isChecked
                    var endYear: Int? = null
                    if (!isCurrent) {
                        endYear = Integer.parseInt(txtEndYear.text.toString())
                    }
                    val experience =
                        Experience(employer, jobTitle, startYear, endYear, description, isCurrent)

                    //____________________________________POST EXPERIENCE__________________________________________
                    val jsonData = Json.encodeToString(experience)
                    Toast.makeText(this, jsonData, Toast.LENGTH_LONG).show()
                    /*
                    val url = URL("https://example.com/api/endpoint")
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "POST"
                    connection.setRequestProperty("Content-Type", "application/json; charset=utf-8")
                    connection.doOutput = true
                    val outputStream = connection.outputStream
                    outputStream.write(jsonData.toByteArray(Charset.defaultCharset()))
                    outputStream.close()
                    val responseCode = connection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Success
                    } else {
                        // Error
                    }
                    connection.disconnect()
                     */
                    //____________________________________POST EXPERIENCE__________________________________________
                }
            } else {
                Toast.makeText(
                    this@AddExperienceActivity,
                    "Missing fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

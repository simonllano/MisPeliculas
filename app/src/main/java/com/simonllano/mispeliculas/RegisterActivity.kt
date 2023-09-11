package com.simonllano.mispeliculas

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.simonllano.mispeliculas.databinding.ActivityRegisterBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    private var fechaNacimiento: String = ""
    private var cal = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = registerBinding.root
        setContentView(view)

        val dateSetListener =
            DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val format = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(format,Locale.US)
                 fechaNacimiento = sdf.format(cal.time).toString()
                registerBinding.dataPickerEditText.setText(fechaNacimiento).toString()
            }
        registerBinding.dataPickerEditText.setOnClickListener{
            DatePickerDialog( this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }



        registerBinding.registerButton.setOnClickListener {
            Log.d("button","clicked")
            val email = registerBinding.emailTextInputEditText.text.toString()
            val password = registerBinding.passwordTextInputEditText.text.toString()
            val reppassword = registerBinding.repPasswordTextInputEditText.text.toString()

            var genre = "Femenino"
            val ciudad = registerBinding.citySpinner.selectedItem.toString()


            if(registerBinding.maleRadioButton.isChecked) {
                genre = "Masculino"
            }

            var favoritesGenre = ""

            if(registerBinding.actionCheckBox.isChecked)
                favoritesGenre= favoritesGenre + " " +"Accion"

            if(registerBinding.adventureCheckBox.isChecked)
                favoritesGenre = favoritesGenre + " "+ "Aventura"

            if(registerBinding.loveCheckBox.isChecked)
                favoritesGenre =favoritesGenre +" " + "Amor"

            if(registerBinding.dramaCheckBox.isChecked)
                favoritesGenre = favoritesGenre + " " + "Drama"

            if(registerBinding.comedyCheckBox.isChecked)
                favoritesGenre= favoritesGenre+ " "+ "Comedia"

            if(registerBinding.fictionCheckBox.isChecked)
                favoritesGenre= favoritesGenre+ " "+ "Ficcion"

            if(registerBinding.fantasyCheckBox.isChecked)
                favoritesGenre=favoritesGenre + " "+ "Fantasia"

            if(registerBinding.suspenseCheckBox.isChecked)
                favoritesGenre = favoritesGenre +" "+ "Suspenso"

            if(registerBinding.terrorCheckBox.isChecked)
                favoritesGenre = favoritesGenre + " " + "Terror"


            if(password ==reppassword){
               val info = email +"\n" + password + "\n" + genre + "\n" + favoritesGenre + "\n" + ciudad
                registerBinding.infoTextView.text = info
            }
            else{
                Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_LONG).show()

            }




        }


    }
}
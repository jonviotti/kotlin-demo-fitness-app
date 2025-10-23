package co.example.fitnesstracker

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.DialogCompat


class ImcActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editWeight = findViewById(R.id.edit_imc_weight)
        editHeight = findViewById(R.id.edit_imc_height)

        val btnSend: Button = findViewById(R.id.btn_imc_send)

        btnSend.setOnClickListener {
            if (!validateInput()) {
                Toast.makeText(this, R.string.fields_message, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()

            val result = calcIMC(weight,height)
            Log.d("Test", "Resultado $result")

            val imcResponseID = imcResponse(result)
            //Toast.makeText(this,imcResponseID,Toast.LENGTH_SHORT).show()

            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Titulo Teste")
            alertDialog.setMessage(R.string.calc)
            alertDialog.setPositiveButton("texto botao", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }

            })

            val d = alertDialog.create()
            d.show()
        }

    }

    private fun validateInput(): Boolean {

        if (editWeight.text.toString().isNotEmpty()
            && editHeight.text.toString().isNotEmpty()
            && !editWeight.text.toString().startsWith("0")
            && !editHeight.text.toString().startsWith("0")
        ) {
            return true
        }
            return false

        //Outra opcao de return

//        return (editWeight.text.toString().isNotEmpty()
//                && editHeight.text.toString().isNotEmpty()
//                && !editWeight.text.toString().startsWith("0")
//                && !editHeight.text.toString().startsWith("0")
//                )
    }

    @StringRes
    private fun imcResponse(imc:Double): Int{
       return when{
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
//        if (imc < 15.0) {
//            return R.string.imc_severely_low_weight
//        }else if (imc < 16.0){
//            return R.string.imc_very_low_weight
//        }else if(imc < 18.5){
//            return  R.string.imc_low_weight
//        }else if (imc < 25.0){
//            return R.string.normal
//        }else if (imc < 30.0){
//            return R.string.imc_high_weight
//        }else if (imc < 35.0){
//            return R.string.imc_so_high_weight
//        }else if (imc > 40.0){
//            return R.string.imc_severely_high_weight
//        }else{
//            return R.string.imc_extreme_weight
//        }
    }

    private fun calcIMC(weight: Int, height: Int): Double{
        return  (weight / ( (height / 100.0) * (height / 100.0))).toDouble()
    }

}
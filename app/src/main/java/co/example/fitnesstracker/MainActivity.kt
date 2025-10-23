package co.example.fitnesstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private lateinit var btn_imc:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_imc = findViewById(R.id.btn_imc)

        btn_imc.setOnClickListener {
            //navegar para prox tela
            val i = Intent(this, ImcActivity::class.java) // codigo padrao para abrir uma segunda activity
            startActivity(i)
        }
    }
}
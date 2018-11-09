package br.com.esmael.iotapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        botaoCriarConta.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        botaoConectar.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                    email.text.toString(),
                    senha.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(Intent(this, ControlActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                            this@LoginActivity,
                            "Email/senha inválidos. Tente novamente",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

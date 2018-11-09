package br.com.esmael.iotapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.esmael.iotapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        botaoCriarConta.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                    email.text.toString(),
                    senha.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    salvarNoRealtimeDatabase()
                } else {
                    Toast.makeText(
                            this@SignupActivity,
                            "Falha ao gravar os dados.",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun salvarNoRealtimeDatabase() {
        val user = User(nome.text.toString(), email.text.toString(), fone.text.toString())

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .setValue(user)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Erro ao criar o usuário", Toast.LENGTH_SHORT).show()
                    }
                }
    }

}

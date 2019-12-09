package view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.beheer.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.view.*
import com.example.beheer.NavigationHost

class LoginFragment : Fragment() {


    //firebase Authentication
    private lateinit var firebaseAuth: FirebaseAuth
    //text fields
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(com.example.beheer.R.layout.fragment_login, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)


        val isConnected = activity?.connected()

        if (isConnected!!) {
            view.login_button.setOnClickListener {
                emailEditText = view.email_input
                passwordEditText = view.password_input

                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(
                        email, password
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            (activity as NavigationHost).navigateTo(DisplayCarFragment(), true)
                        } else {
                            Toast.makeText(
                                activity?.applicationContext,
                                "Firebase :Login error, please try again!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {

                    Toast.makeText(
                        activity?.applicationContext,
                        "Password and Email must be entered",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        } else {
            Toast.makeText(
                activity?.applicationContext,
                "Error! Check your Internet Connection",
                Toast.LENGTH_LONG
            ).show()
        }

        view.register_button.setOnClickListener {

            (activity as NavigationHost).navigateTo(RegisterFragment(), false)
        }
        return view

    }

}
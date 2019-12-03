package view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.beheer.NavigationHost
import com.example.beheer.R
import com.example.chareta.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_registration.view.*


class LoginFragment : Fragment() {

    //firebase Authentication
    private lateinit var firebaseAuth: FirebaseAuth
    //text fields
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        emailEditText = view.email_input
        passwordEditText = view.password_input

        firebaseAuth = FirebaseAuth.getInstance()

        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)



        view.login_button.setOnClickListener {
            firebaseAuth.signInWithEmailAndPassword(emailEditText.toString(), passwordEditText.toString()).addOnCompleteListener {
                if (it.isSuccessful) {
                    //            Navigation.createNavigateOnClickListener(R.id.postedItemFragment)
                    (activity as NavigationHost).navigateTo(DisplayCarFragment(), true)
                } else {
                    Toast.makeText(activity?.applicationContext, "Error, please try again!", Toast.LENGTH_LONG).show()
                }
            }

        }
        //navigate to register fragment
        view.register_button.setOnClickListener {
            //Navigation.createNavigateOnClickListener(R.id.registerFragment)
            (activity as NavigationHost).navigateTo(RegisterFragment(), true)
        }
        return view

    }
}
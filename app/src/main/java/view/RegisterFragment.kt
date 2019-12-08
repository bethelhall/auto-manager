package view
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.beheer.MainActivity
import com.example.beheer.NavigationHost
import com.example.beheer.R
import com.example.beheer.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import data.model.User
import kotlinx.android.synthetic.main.fragment_registration.view.*

class RegisterFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var phoneEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var firebaseAuth: FirebaseAuth
    private var isMatch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        firebaseAuth = FirebaseAuth.getInstance()

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)
//        val isConnected = activity?.connected()
        /////
        firstNameEditText = view.register_first_name
        emailEditText = view.register_email
        passwordEditText = view.register_password
        phoneEditText = view.register_phone
        confirmPasswordEditText = view.register_confirm_password

        view.back_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(
                LoginFragment(),
                false
            )
        }
        view.register_button.setOnClickListener {
//
//            if (isConnected!!) {
                firebaseAuth.createUserWithEmailAndPassword(
                   "gemme@gmail.com",
                   "123456789"
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
//                        userViewModel.insertUser(readFields())
                        Toast.makeText(
                            activity?.applicationContext,
                            "Congratulations,you are registered",
                            Toast.LENGTH_LONG
                        ).show()
                        (activity as NavigationHost).navigateTo(DisplayCarFragment(), true)

                    } else {
                        Toast.makeText(
                            activity?.applicationContext,
                            "Registration failed",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

//            } else {
//                Toast.makeText(
//                    activity?.applicationContext,
//                    "Not connected to a network!",
//                    Toast.LENGTH_LONG
//                ).show()
//
//
//            }
        }

        return view
    }

//    private fun readFields() = User(
//        0,
//        firstNameEditText.text.toString(),
//        emailEditText.text.toString(),
//        phoneEditText.text.toString(),
//        passwordEditText.text.toString(),
//        confirmPasswordEditText.text.toString()
//
//    )

    private fun clearFields() {
        firstNameEditText.setText("")
        emailEditText.setText("")
        phoneEditText.setText("")
        passwordEditText.setText("")
        confirmPasswordEditText.setText("")
    }

    private fun checkPasswowrdMatch(pwd: String, pwd2: String): Boolean {
        isMatch = pwd === pwd2
        return true

    }
}
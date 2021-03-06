package com.example.applogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var btnInicio: Button
    private lateinit var btnRegistro: Button
    private lateinit var btnPhone: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = view.findViewById(R.id.loginTextEmail)
        pass = view.findViewById(R.id.loginTextPassword)
        btnInicio = view.findViewById(R.id.loginButtonInicio)
        btnRegistro = view.findViewById(R.id.loginButtonRegister)
        btnPhone = view.findViewById(R.id.loginButtonPhone)

        btnInicio.setOnClickListener {
                view:View -> print("BTN INICIO")

                 signIn(view,email.text.toString(),pass.text.toString())
        }

        btnRegistro.setOnClickListener {
                view:View -> print("BTN REGISTRO")

                view.findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }

        btnPhone.setOnClickListener {
                view:View ->
                view.findNavController().navigate(R.id.action_loginFragment_to_phoneFragment)

        }

    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) println(">> ya usuario") else println(">> no hay usuario")
    }

    fun signIn(view: View,email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    println(">> muy bien")

                    view.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)

                } else {
                    println(">> login no exitoso")
                    Toast.makeText(requireActivity().applicationContext, "usuario No valido", Toast.LENGTH_LONG)
                }
            }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
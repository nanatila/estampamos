package com.example.applogin.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.applogin.R
import com.example.applogin.databinding.FragmentAdminDetailDialogBinding
import com.example.applogin.model.DBHelper
import java.lang.ref.ReferenceQueue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdminDetailDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminDetailDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var nombre:String
    private lateinit var direccion:String
    private lateinit var telefono:String
    private lateinit var correo:String

    private lateinit var informacionDBHelper:DBHelper

    fun newInstance(nombre:String, direccion:String, telefono:String, correo:String,):AdminDetailDialogFragment{
        val f = AdminDetailDialogFragment()
        val args = Bundle()
        args.putString("nombre",nombre)
        args.putString("direccion",direccion)
        args.putString("telefono",telefono)
        args.putString("correo",correo)

        f.arguments = args
        return f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nombre = it.getString("nombre").toString()
            direccion = it.getString("direccion").toString()
            telefono = it.getString("telefono").toString()
            correo = it.getString("correo").toString()

        }
        informacionDBHelper = DBHelper(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var binding = FragmentAdminDetailDialogBinding.bind(view)

        binding.ibPhotoAdmin.setImageResource(R.mipmap.ic_launcher)
        binding.etNameAdmin.setText(nombre)
        binding.etAddress.setText(direccion)
        binding.etPhone.setText(telefono)
        binding.etemail.setText(correo)
        binding.btSaveAdmin.setOnClickListener {

            if(binding.etNameAdmin.text.isNotBlank() &&
            binding.etAddress.text.isNotBlank() &&
            binding.etPhone.text.isNotBlank() &&
            binding.etemail.text.isNotBlank()){

                informacionDBHelper.edit(1,
                    binding.etNameAdmin.text.toString(),
                    binding.etAddress.text.toString(),
                    binding.etPhone.text.toString(),
                    binding.etemail.text.toString())

                Toast.makeText(requireContext(),"Se guardaron los datos", Toast.LENGTH_LONG).show()

                binding.etNameAdmin.text.clear()
                binding.etAddress.text.clear()
                binding.etPhone.text.clear()
                binding.etemail.text.clear()

            }else{
                Toast.makeText(requireContext(),"Error al guardare", Toast.LENGTH_LONG).show()
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
         * @return A new instance of fragment AdminDetailDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdminDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
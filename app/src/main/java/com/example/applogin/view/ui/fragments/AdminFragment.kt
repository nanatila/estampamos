package com.example.applogin.view.ui.fragments

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.applogin.R
import com.example.applogin.databinding.FragmentAdminBinding
import com.example.applogin.databinding.FragmentAdminDetailDialogBinding
import com.example.applogin.model.DBHelper
import com.example.applogin.model.Tables

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdminFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var informacionDBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        informacionDBHelper = DBHelper(requireActivity())

        val db: SQLiteDatabase = informacionDBHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + Tables.information["TABLE_NAME"] + " WHERE id=1",null)

        if(!cursor.moveToFirst()){
            informacionDBHelper.insert(
                "nombre",
                "direccion",
                "telefono",
                "email@app.com"
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var binding = FragmentAdminBinding.bind(view)
        val button = view.findViewById<Button>(R.id.navigate_edition)

        val db: SQLiteDatabase = informacionDBHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + Tables.information["TABLE_NAME"],null)

        if(cursor.moveToFirst()){
            do{
                binding.etNameAdmin.setText(cursor.getString(1).toString())
                binding.etAddress.setText(cursor.getString(2).toString())
                binding.etPhone.setText(cursor.getString(3).toString())
                binding.etemail.setText(cursor.getString(4).toString())
            }while(cursor.moveToNext())
        }

        button?.setOnClickListener {

            //findNavController().navigate(R.id.adminDetailFragmentDialog, null)

            var nombre = binding.etNameAdmin.text.toString()
            var direccion = binding.etAddress.text.toString()
            var telefono = binding.etPhone.text.toString()
            var correo = binding.etemail.text.toString()

            var dialogFragment = AdminDetailDialogFragment().newInstance(
                nombre,
                direccion,
                telefono,
                correo
            )
            dialogFragment.show(childFragmentManager, "AdminDetailDialogFragment")

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdminFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdminFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
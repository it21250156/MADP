package com.example.madproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import java.io.FileDescriptor
import java.io.PrintWriter


class Profile : Fragment() {

    var var1: NavController ?= null
    var btnReset: Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val vv = inflater.inflate(R.layout.fragment_profile, container, false)

//        val resetBtn = vv.findViewById<Button>(R.id.btn_profile_resetpw)
//
//        resetBtn.setOnClickListener {
//            val resetPWFragment = ResetPW()
//            val transaction : FragmentTransaction = fragmentManager.beginTransaction()
//            transaction.replace(R.id.mainlayout,resetPWFragment)
//            transaction.commit()
//        }


        return vv
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        var1 = Navigation.findNavController(view)
//        view.findViewById<Button>(R.id.btn_profile_resetpw)?.setOnClickListener(this)
//
//    }
//    override fun onClick(p0: View?) {
//        var1?.navigate(R.id.action_profile2_to_resetPW)
//    }


}
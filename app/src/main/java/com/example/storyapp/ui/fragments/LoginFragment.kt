package com.example.storyapp.ui.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.storyapp.R
import com.example.storyapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edLoginPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.apply {
                    if (s.toString().length < 8) {
                        errorPass.visibility = View.VISIBLE
                        edLoginPassword.setBackgroundDrawable(context?.let {
                            ContextCompat.getDrawable(
                                it, R.drawable.custom_input_error_rounded)
                        })
                    } else {
                        errorPass.visibility = View.GONE
                        edLoginPassword.setBackgroundDrawable(context?.let {
                            ContextCompat.getDrawable(
                                it, R.drawable.custom_input_rounded)
                        })
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.edLoginEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.apply {
                    if (isValidEmail(s.toString())) {
                        errorEmail.visibility = View.GONE
                        edLoginEmail.setBackgroundDrawable(context?.let {
                            ContextCompat.getDrawable(
                                it, R.drawable.custom_input_rounded)
                        })
                    } else {
                        errorEmail.visibility = View.VISIBLE
                        edLoginEmail.setBackgroundDrawable(context?.let {
                            ContextCompat.getDrawable(
                                it, R.drawable.custom_input_error_rounded)
                        })
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.includeIntentRegister.registerIntent.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.registerIntent) {
            val registerFragment = RegisterFragment()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(
                    R.id.auth_fragment_container,
                    registerFragment,
                    RegisterFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isValidEmail(email: String): Boolean {
        val regexEmail = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return regexEmail.matches(email)
    }
}

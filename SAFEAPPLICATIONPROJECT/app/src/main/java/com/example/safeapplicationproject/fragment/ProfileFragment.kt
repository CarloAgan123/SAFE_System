package com.example.safeapplicationproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.safeapplicationproject.R
import com.example.safeapplicationproject.data.users
import com.example.safeapplicationproject.databinding.FragmentAccountBinding
import com.google.firebase.database.*

class ProfileFragment : Fragment(R.layout.fragment_account) {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchDataFromDatabase()
    }

    private fun fetchDataFromDatabase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child("54b5e701-3691-42db-8f97-4bca7cbd8d4e")
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.getValue(users::class.java)
                    if (user != null) {
                        binding.tvName.text = user.Fullname ?: "No Name"
                        binding.tvAddress.text = user.Address ?: "No Address"
                        binding.tvContact.text = user.Phone ?: "No Contact"
                        binding.tvGender.text = user.Age.toString() ?: "No Gender"
                        binding.tvAge.text = user.Age.toString() ?: "No Age"
                        binding.tvRole.text = user.Role ?: "No Room Type"

                        Glide.with(requireContext())
                            .load(user.Image)
                            .into(binding.userImage)
                    }
                } else {
                    Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Error loading data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

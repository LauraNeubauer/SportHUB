package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.MyChatsFragmentBinding
import com.example.myapplication.model.Chat

class MyChatsFragment : Fragment() {


    private lateinit var binding: MyChatsFragmentBinding
    private val personViewModel: ViewModel by activityViewModels()
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyChatsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btGroups.setOnClickListener {
            findNavController().navigate(R.id.action_myChatsFragment_to_allChatsFragment)
        }

        firebaseViewModel.profileRef.collection("groups").addSnapshotListener { value, error ->
            if (error == null && value != null) {
                val myChats = value.documents.map { document ->
                    Chat(
                        groupID = document.get("groupID") as? Int ?: 0,
                        groupName = document.get("groupName") as? String ?: "",
                        groupPic = document.get("groupPic") as? Int ?: 0
                    )
                }
                Log.e("Groups", "${myChats.size}")
            }
        }

        firebaseViewModel.profileRef.collection("groups").document("QwUaYQSIZ3FgFs4j7B4u")
            .addSnapshotListener { value, error ->
                if (error == null && value != null) {
                    val myGroup = value.toObject(Chat::class.java)
                    binding.tvTitle.setText(myGroup!!.groupName)

                }
            }
    }
}
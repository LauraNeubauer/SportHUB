package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapter.ChatDetailAdapter
import com.example.myapplication.databinding.ChatDetailFragmentBinding
import com.example.myapplication.viewmodel.FirebaseViewModel
import com.example.myapplication.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// Die Chat-Detail-Ansicht Klasse
class ChatDetailFragment : Fragment() {

    //die benötigten Variablen für das Binding und die ViewModels
    private lateinit var binding: ChatDetailFragmentBinding
    private val personMainViewModel: MainViewModel by activityViewModels()
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()

    // Wird aufgerufen, um die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflatiere das Layout für dieses Fragment
        binding = ChatDetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Rufe die Methode auf, um meine Chats von Firebase abzurufen
        firebaseViewModel.fetchMyChats()

        // Beobachte Änderungen im aktuellen Chat im ViewModel und aktualisiere die Ansicht entsprechend
        firebaseViewModel.getCurrentChat.observe(viewLifecycleOwner) {
            // Setze den Gruppennamen und die Nachrichten im RecyclerView-Adapter
            binding.tvChatName.text = it.groupName
            binding.rvMessages.adapter = ChatDetailAdapter(it.messages.sortedByDescending { it.timestamp }.reversed().toMutableList(), personMainViewModel)
            val itemCount = it.messages.size
            // Scrolle zum letzten Element, um die neueste Nachricht anzuzeigen
            if (itemCount > 0) {
                binding.rvMessages.scrollToPosition(itemCount - 1)
            }
        }

        // Navigiere zum MyChatsFragment, wenn der "Zurück"-Button geklickt wird
        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.myChatsFragment)
        }

        // Behandele das Senden einer Nachricht
        binding.btSend.setOnClickListener {
            // Überprüfung, ob der Text nicht leer ist
            if (binding.tietText.text!!.isNotEmpty()) {
                // Extrahierung den Text und füge die Nachricht zum aktuellen Chat hinzu
                var text = binding.tietText.text!!.toString()
                firebaseViewModel.addMessageToChat(
                    firebaseViewModel.getCurrentChat.value!!.groupID!!, text, firebaseViewModel.getName.value!!, getCurrentTime()
                )
                // Löscht den Text im Eingabefeld
                binding.tietText.text!!.clear()
                // Aktualisiere die Nachrichtenansicht mit den neuesten Nachrichten
                lifecycleScope.launch {
                    // Verzögere die Aktualisierung der Nachrichtenansicht
                    delay(1500)
                    var updatedMessages = firebaseViewModel.getCurrentChat.value!!.messages
                    binding.rvMessages.adapter = ChatDetailAdapter(updatedMessages.sortedByDescending { it.timestamp }.reversed().toMutableList(), personMainViewModel)
                }
            } else {
                // Wenn der Text leer ist, lösche das Eingabefeld
                binding.tietText.text!!.clear()
            }
        }
    }

    // Funktion zur Rückgabe der aktuellen Uhrzeit als formatierten String
    fun getCurrentTime(): String {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return currentTime.format(formatter)
    }
}
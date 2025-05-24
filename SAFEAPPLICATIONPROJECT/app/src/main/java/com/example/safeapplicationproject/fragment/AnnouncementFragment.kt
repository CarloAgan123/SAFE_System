package com.example.safeapplicationproject.fragment

import androidx.fragment.app.Fragment
import com.example.safeapplicationproject.R
import com.example.safeapplicationproject.databinding.FragmentContactBinding
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class AnnouncementFragment: Fragment(R.layout.fragment_announcement) {

    private lateinit var binding: FragmentContactBinding
    private lateinit var realTimeText: TextView
    private val handler = Handler(Looper.getMainLooper())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the TextView
        realTimeText = view.findViewById(R.id.realTimeText)

        // Start updating the real-time date and time
        startRealTimeClock()
    }

    // Function to start updating real-time date and time
    private fun startRealTimeClock() {
        val updateTimeRunnable = object : Runnable {
            override fun run() {
                // Get current date and time
                val currentTime = getCurrentDateTime()

                // Update the TextView with the current date and time
                realTimeText.text = currentTime

                // Repeat every second
                handler.postDelayed(this, 1000)
            }
        }

        // Start the handler to update the time
        handler.post(updateTimeRunnable)
    }

    // Function to get the current date and time in the desired format
    private fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null) // Stop handler when fragment is destroyed
    }
}
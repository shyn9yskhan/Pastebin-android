package com.example.pastebinapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.pastebinapp.databinding.FragmentSearchBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "SearchFragment"

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBackendResponse()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getBackendResponse() {
        lifecycleScope.launch {
            val response = try {
                RetrofitInstance.api.getResponse()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection", e)
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response", e)
                return@launch
            }

            if (response.isSuccessful) {
                response.body()?.let { resp ->
                    binding.textViewX.text = resp.message
                } ?: run {
                    binding.textViewX.text = "Error: Response body is null"
                }
            } else {
                Log.d(TAG, "Response not successful: ${response.code()}")
            }
        }
    }
}

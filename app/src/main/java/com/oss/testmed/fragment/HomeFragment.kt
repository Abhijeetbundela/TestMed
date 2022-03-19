package com.oss.testmed.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oss.testmed.R
import com.oss.testmed.adapter.TestAdapter
import com.oss.testmed.databinding.FragmentHomeBinding
import com.oss.testmed.dialog.ProgressDialog
import com.oss.testmed.retrofit.NetworkClient
import com.oss.testmed.viewModel.TestViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var testAdapter: TestAdapter

    private val testViewModel by lazy { ViewModelProvider(this).get(TestViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        testAdapter = TestAdapter(requireContext(), arrayListOf(1, 2, 3, 4, 5, 6))

        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)

        setUpRecyclerView()
        setUpObserver()

        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.rvTest.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = testAdapter
            setHasFixedSize(true)
            recycledViewPool.setMaxRecycledViews(1, 0)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button1 -> callApi("${R.id.button1} B1")
            R.id.button2 -> callApi("${R.id.button2} B2")
            R.id.button3 -> callApi("${R.id.button3} B3")
            R.id.button4 -> callApi("${R.id.button4} B4")
        }
    }

    private fun setUpObserver() {
        val dialog = ProgressDialog(requireActivity())

        testViewModel.isLoading.observe(requireActivity(), {
            if (it) {
                dialog.showDialog()
            } else {
                dialog.hideDialog()
            }
        })
        testViewModel.errorMessage.observe(requireActivity(), {
            if (it.isNotEmpty()) {
                Toast.makeText(
                    requireContext(),
                    it,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        testViewModel.tempData.observe(requireActivity(), {
            val data = it.data
            Toast.makeText(
                requireContext(),
                data.rstr,
                Toast.LENGTH_SHORT
            ).show()
        })

    }

    private fun callApi(id: String) {
        val map = HashMap<String, String>()
        map["bname"] = id
        testViewModel.getData(map)
    }

}
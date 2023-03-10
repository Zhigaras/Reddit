package com.zhigaras.reddit.presentation.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhigaras.reddit.R
import com.zhigaras.reddit.presentation.viewModels.FavoriteViewModel

class FavoriteFragment : Fragment() {
    
    companion object {
        fun newInstance() = FavoriteFragment()
    }
    
    private lateinit var viewModel: FavoriteViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        // TODO: Use the ViewModel
    }
    
}
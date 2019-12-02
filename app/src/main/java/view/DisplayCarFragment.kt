package com.example.chareta.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beheer.MainActivity
import com.example.beheer.R
import com.example.beheer.viewmodel.CarViewModel



class DisplayCarFragment : Fragment() {
    private lateinit var carViewModel: CarViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        carViewModel = ViewModelProviders.of(this).get(CarViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.your_posts_fragment, container, false)
        val viewModel = ViewModelProviders.of(this).get(CarViewModel::class.java)
        val activity = activity as MainActivity?
        activity?.hideBottomBar(false)
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

        val isConnected = activity.connected()

        recyclerView = view.recycler_view_manage
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        if (isConnected) {
            itemViewModel.getItemsByUserId(1)
            itemViewModel.getResponses.observe(this, Observer {
                recyclerView.adapter =
                    ManageRecyclerAdapter(it.body()!!, itemViewModel)
            })
        } else {
            itemViewModel.getItemsFromLocal()
            itemViewModel.getResponses.observe(this, Observer {
                recyclerView.adapter =
                    ManageRecyclerAdapter(it.body()!!, itemViewModel)
            })
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.post_item -> (activity as NavigationHost).navigateTo(
                CreatePostFragment(),
                true
            ) // Navigate to the next Fragment
            R.id.logout -> (activity as NavigationHost).navigateTo(LoginFragment(), false)
        }
        return super.onOptionsItemSelected(item)
    }
}
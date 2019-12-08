package view

import adapter.CarRecyclerAdapter
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beheer.MainActivity
import com.example.beheer.NavigationHost
import com.example.beheer.R
import com.example.beheer.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.fragment_display_car.view.*


class DisplayCarFragment : Fragment() {
    private lateinit var carViewModel: CarViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display_car, container, false)
        val activity = activity as MainActivity?

        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)


        val isConnected = activity.connected()

        activity.hideBottomBar(false)

        recyclerView = view.recycler_view_manage
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        if (isConnected) {
            carViewModel.getCars()
            carViewModel.getResponses.observe(viewLifecycleOwner, Observer {
                recyclerView.adapter =
                    CarRecyclerAdapter(it.body()!!, carViewModel)
            })
        } else {
            carViewModel.getCarsFromLocal()
            carViewModel.getResponses.observe(viewLifecycleOwner, Observer {
                recyclerView.adapter =
                    CarRecyclerAdapter(it.body()!!, carViewModel)
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
                RegisterCarFragment(),
                true
            ) // Navigate to the next Fragment
            R.id.logout -> (activity as NavigationHost).navigateTo(LoginFragment(), false)
        }
        return super.onOptionsItemSelected(item)
    }
}
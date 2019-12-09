package view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.beheer.NavigationHost

import com.example.beheer.R
import com.example.beheer.viewmodel.CarViewModel
import data.model.Car
import kotlinx.android.synthetic.main.fragment_car_detail.view.*


class CarDetailFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel

    private lateinit var model: TextView
    private lateinit var price: TextView
    private lateinit var engineType: TextView
    private lateinit var distance: TextView
    private lateinit var manufDate: TextView
    private lateinit var backbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_car_detail, container, false)

        model = view.carname_textview
        distance = view.distance
        price = view.distance
        manufDate = view.manuf_date
        engineType = view.engine_type
        backbtn = view.back_button

        val carId: Long? = arguments?.getLong("carId", 1)

        carViewModel.getCarById(carId!!)
        carViewModel.getResponse.observe(viewLifecycleOwner, Observer { response ->
            val car: Car = response.body()!!
            model.text = car.model
            price.text = car.price.toString()
            distance.text = car.km
            manufDate.text = car.year
            engineType.text = car.engine

        })

        backbtn.setOnClickListener {
            (activity as NavigationHost).navigateTo(DisplayCarFragment(), false)
        }

        return view

    }


    companion object {

        @JvmStatic
        fun newInstance(carId: Long): CarDetailFragment {

            val carDetailFragment = CarDetailFragment()
            val args = Bundle()
            args.putLong("carId", carId)
            carDetailFragment.arguments = args
            return carDetailFragment
        }
    }
}

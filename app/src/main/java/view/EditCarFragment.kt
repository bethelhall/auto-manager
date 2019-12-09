package view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.beheer.NavigationHost

import com.example.beheer.R
import com.example.beheer.viewmodel.CarViewModel
import data.model.Car
import kotlinx.android.synthetic.main.fragment_car_detail.view.*
import kotlinx.android.synthetic.main.fragment_edit_car.view.*


class EditCarFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel

    private lateinit var carName: EditText
    private lateinit var distance: EditText
    private lateinit var price: EditText
    private lateinit var engineType: EditText
    private lateinit var  manufDate: EditText
    private lateinit var submitButton: Button
    private lateinit var backButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_car, container, false)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        carName = view.modelEdit_editext
        engineType = view.engineEdit_editText
        distance = view.distance_coverageEdit_editText
        price = view.priceEdit_Edittext
        backButton = view.backEdit_btn
        submitButton = view.submitEdit_button

        val carId: Long? = arguments?.getLong("carId")
        carViewModel.getCarById(carId!!)
        carViewModel.getResponse.observe(viewLifecycleOwner, Observer { response ->
            val car: Car = response.body()!!
            carName.setText(car.engine)
            engineType.setText(car.engine)
            distance.setText(car.km)
            price.setText(car.price.toString())

        })


        view.submitEdit_button.setOnClickListener {
            readFields()
        }

        view.backEdit_btn.setOnClickListener {

            (activity as NavigationHost).navigateTo(DisplayCarFragment(), true)
        }


        return view

    }

    private fun readFields() = Car(
        1,
        price.text.toString().toLong(),
        carName.text.toString(),
        manufDate.text.toString(),
        distance.text.toString(),
        engineType.text.toString()

    )

    companion object {

        @JvmStatic
        fun newInstance(carId: Long): EditCarFragment {
            val editCarFragment = EditCarFragment()
            val args = Bundle()
            args.putLong("carId", carId)
            editCarFragment.arguments = args
            return editCarFragment
        }

    }
}

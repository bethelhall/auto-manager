package view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.*
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.beheer.MainActivity
import com.example.beheer.NavigationHost
import com.example.beheer.R
import com.example.beheer.viewmodel.CarViewModel
import data.model.Car
import kotlinx.android.synthetic.main.fragment_register_car.*
import kotlinx.android.synthetic.main.fragment_register_car.view.*


class RegisterCarFragment : Fragment() {

    private lateinit var model: EditText
    private lateinit var engineType: EditText
    private lateinit var date: TextView
    private lateinit var price: EditText
    private lateinit var distance: EditText
    private lateinit var backbtn: Button
    private lateinit var postbtn: Button
    private lateinit var postdate_btn: Button
    @RequiresApi(Build.VERSION_CODES.O)


    lateinit var carViewModel: CarViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_car, container, false)
        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)
        val isConnected = activity?.connected()

        model = view.model_editext
        price = view.price_Edittext
        date = view.manufacture_date
        engineType = view.engine_type_editText
        distance = view.distance_coverage_editText

        postbtn = view.submit_button
        postdate_btn = view.pickdate_btn
        backbtn = view.back_btn
        val c = java.util.Calendar.getInstance()
        val year = c.get(java.util.Calendar.YEAR)
        val month = c.get(java.util.Calendar.MONTH)
        val day = c.get(java.util.Calendar.DAY_OF_MONTH)

        backbtn.setOnClickListener {
            (activity as NavigationHost).navigateTo(DisplayCarFragment(), true)
        }

        postbtn.setOnClickListener {
            val car:Car = readFields()
            if (isConnected!!) {
                carViewModel.insertCar(readFields())
                carViewModel.insertCar(car)

                (activity as NavigationHost).navigateTo(DisplayCarFragment(), true)

                Toast.makeText(context, "Car Registered Successfully!!", Toast.LENGTH_LONG)
                    .show()
            }
            clearFields()
        }


        postdate_btn.setOnClickListener {

            val dpd =
                DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { view, myear, mmonth, mday ->
                        date.text = " $mday/$mmonth/$myear"
                    },
                    year,
                    month,
                    day
                )
            dpd.show()

        }
        return view
    }

    private fun updateFields(car: Car) {
        car.run {
            model_editext.setText(car.model)
            price_Edittext.setText(car.price.toString())
            manufacture_date.setText(car.yr)
            engine_type_editText.setText(car.engine)
            distance_coverage_editText.setText(car.km)
        }
    }

    private fun readFields() = Car(
        0,
        price.text.toString().toLong(),
        model.text.toString(),
        date.text.toString(),
        distance.text.toString(),
        engineType.text.toString()
    )

    private fun clearFields() {
        model.setText("")
        price.setText("")
        date.setText("")
        engineType.setText("")
        distance.setText("")
    }


}


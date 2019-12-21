package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.RecyclerView
import com.example.beheer.R
import com.example.beheer.viewmodel.CarViewModel
import data.model.Car
import data.model.CarWrapper
import view.CarDetailFragment
import view.EditCarFragment


class CarRecyclerAdapter(
    private var allCars: CarWrapper,
    private var carViewModel: CarViewModel,
    private var fragment: FragmentManager
) : RecyclerView.Adapter<CarRecyclerAdapter.ManageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewCar = inflater.inflate(R.layout.manage_layout, parent, false)
        return ManageViewHolder(recyclerViewCar)
    }

    override fun getItemCount(): Int {
        return allCars.embeddedCars.allCars.size
    }

    override fun onBindViewHolder(holder: ManageViewHolder, position: Int) {
        val car = allCars.embeddedCars.allCars[position]
        val carId = allCars.embeddedCars.allCars[position].id
        holder.model.text = car.model
        holder.eng_type.text = car.engine
        holder.price.text = car.price.toString()
        holder.manuf_date.text = car.yr

        holder.deleteButton.setOnClickListener {

            var carID: Long? = carId
            carViewModel.deleteCar(carID!!)
        }

        holder.editButton.setOnClickListener {
            fragment.beginTransaction()
                .replace(
                    R.id.container,
                    EditCarFragment.newInstance(allCars.embeddedCars.allCars[position].id)
                )
                .addToBackStack(null)
                .commit()

        }

        holder.carView.setOnClickListener {
            fragment.beginTransaction()
                .replace(
                    R.id.container,
                    CarDetailFragment.newInstance(allCars.embeddedCars.allCars[position].id)
                )
                .addToBackStack(null)
                .commit()
        }

    }

    class ManageViewHolder(var carView: View) : RecyclerView.ViewHolder(carView) {
        var model: TextView = carView.findViewById(R.id.model_id)
        var price: TextView = carView.findViewById(R.id.price_id)
        var eng_type: TextView = carView.findViewById(R.id.engine_id)
        var km: TextView = carView.findViewById(R.id.km_id)
        var manuf_date: TextView = carView.findViewById(R.id.date_id)
        val deleteButton: Button = carView.findViewById(R.id.delete_button)
        val editButton: Button = carView.findViewById(R.id.edit_button)
    }
}
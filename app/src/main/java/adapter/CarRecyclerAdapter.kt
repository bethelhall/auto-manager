package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager

import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update
import com.example.beheer.R
import com.example.beheer.viewmodel.CarViewModel
import data.model.CarWrapper
import kotlinx.android.synthetic.main.activity_main.view.*
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
        holder.manuf_date.text = car.year




        holder.deleteButton.setOnClickListener {

            carViewModel.deleteCar(carId)
            notifyDataSetChanged()
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
        var model: TextView = carView.findViewById(R.id.car_name_manage)
        var price: TextView = carView.findViewById(R.id.price_manage)
        var eng_type: TextView = carView.findViewById(R.id.engine_manage)
        var manuf_date: TextView = carView.findViewById(R.id.manufacture_date_manage)
        val deleteButton: Button = carView.findViewById(R.id.delete_button)
        val editButton: Button = carView.findViewById(R.id.edit_button)
    }
}
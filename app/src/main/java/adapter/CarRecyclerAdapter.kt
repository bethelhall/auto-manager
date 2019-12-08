package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.beheer.R
import com.example.beheer.viewmodel.CarViewModel
import data.model.CarWrapper


class CarRecyclerAdapter(
    private var allCars: CarWrapper,
    private var carViewModel: CarViewModel
) :
    RecyclerView.Adapter<CarRecyclerAdapter.ManageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.manage_layout, parent, false)
        return ManageViewHolder(recyclerViewItem)
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
        holder.manuf_date.text = car.year.toString()

        holder.deleteButton.setOnClickListener {
            carViewModel.deleteCar(carId)
        }
        holder.editButton.setOnClickListener {

            //move to edit fragment
        }
    }

    class ManageViewHolder(carView: View) : RecyclerView.ViewHolder(carView) {

        var model: TextView = carView.findViewById(R.id.car_name_manage)
        var price: TextView = carView.findViewById(R.id.price_manage)
        var eng_type: TextView = carView.findViewById(R.id.engine_manage)
        var manuf_date: TextView = carView.findViewById(R.id.manufacture_date_manage)
        val deleteButton: Button = carView.findViewById(R.id.delete_button)
        val editButton: Button = carView.findViewById(R.id.edit_button)
    }
}
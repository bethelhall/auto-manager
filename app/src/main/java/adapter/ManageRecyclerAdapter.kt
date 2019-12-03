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


class ManageRecyclerAdapter(private var allCars: CarWrapper, private var carViewModel: CarViewModel) :
    RecyclerView.Adapter<ManageRecyclerAdapter.ManageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.manage_layout, parent, false)
        return ManageViewHolder(recyclerViewItem)
    }

    override fun getItemCount(): Int {
        return allCars.embeddedItems.allCar.size
    }

    override fun onBindViewHolder(holder: ManageViewHolder, position: Int) {
        val car = allCars.embeddedItems.allCar[position]
        val carId = allCars.embeddedItems.allCar[position].id
//        holder.itemName.text = item.item_name
//        holder.startingPrice.text = item.starting_price.toString()
//        holder.expiryDate.text = item.expiry_date

        holder.deleteButton.setOnClickListener {
            carViewModel.deleteCar(carId)
        }
    }


    class ManageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.item_name_manage)
        var startingPrice: TextView = itemView.findViewById(R.id.starting_price_manage)
        var expiryDate: TextView = itemView.findViewById(R.id.expiry_date_manage)

        val deleteButton: Button = itemView.findViewById(R.id.withdraw_button)
    }
}
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.ShoppingItem

class ShoppingListAdapter(
    private val shoppingList: MutableList<ShoppingItem>,
    private val deleteListener: (ShoppingItem) -> Unit
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingItemViewHolder>() {

    class ShoppingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.tv_item_name)
        val itemQuantity: TextView = itemView.findViewById(R.id.tv_item_quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shopping_list, parent, false)
        return ShoppingItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val currentItem = shoppingList[position]
        holder.itemName.text = currentItem.name
        holder.itemQuantity.text = "x${currentItem.quantity}"

        // Tambahkan listener untuk menghapus item
        holder.itemView.setOnLongClickListener {
            deleteListener(currentItem)
            true
        }
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    // Fungsi baru untuk memperbarui daftar
    fun updateList(newList: List<ShoppingItem>) {
        shoppingList.clear()
        shoppingList.addAll(newList)
        notifyDataSetChanged()
    }
}
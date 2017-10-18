package brittany.varnom.com.fruitapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class FruitAdapter(var fruitList: List<FruitData>) : RecyclerView.Adapter<FruitAdapter.FruitViewHolder>(){
    override fun onBindViewHolder(holder: FruitViewHolder?, position: Int) {
        val fruit = fruitList[position]
        holder?.bind(fruit)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FruitViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.fruit_view_holder, parent, false)
        return FruitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    inner class FruitViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(fruit : FruitData) {
            val fruitIcon = itemView.findViewById<View>(R.id.ic_fruit) as ImageView
            fruitIcon.setImageResource(fruit.imageRes)
            val fruitName = itemView.findViewById<View>(R.id.ic_fruit_name) as TextView
            fruitName.text = fruit.type
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, FruitDetailsActivity::class.java)
                intent.putExtra("FRUIT_DATA", fruit)
                itemView.context.startActivity(intent)
            }
        }
    }

    fun updateFruit(fruitList: List<FruitData>) {
        this.fruitList = fruitList
        notifyDataSetChanged()
    }


}
package brittany.varnom.com.fruitapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class FruitAdapter(var fruitList: List<FruitData>) : RecyclerView.Adapter<FruitAdapter.FruitViewHolder>() {
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

    inner class FruitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(fruit: FruitData) {
            val fruitImage = itemView.findViewById<View>(R.id.ic_fruit) as ImageView
            modifyFruitData(fruit)
            fruitImage.setImageResource(fruit.imageRes)
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

    fun modifyFruitData(fruit: FruitData) {
        /*
         When rotating the screen without this line, the fruit type has already been capitalised,
         meaning the names do not match and the placeholder image is loaded for everything.
        */
        val fruitType = fruit.type.toLowerCase()
        when (fruitType) {
            "apple" -> fruit.imageRes = R.drawable.img_apple
            "banana" -> fruit.imageRes = R.drawable.img_banana
            "blueberry" -> fruit.imageRes = R.drawable.img_blueberry
            "orange" -> fruit.imageRes = R.drawable.img_orange
            "pear" -> fruit.imageRes = R.drawable.img_pear
            "strawberry" -> fruit.imageRes = R.drawable.img_strawberry
            "kumquat" -> fruit.imageRes = R.drawable.img_kumquat
            "pitaya" -> fruit.imageRes = R.drawable.img_pitaya
            "kiwi" -> fruit.imageRes = R.drawable.img_kiwi
            else -> fruit.imageRes = R.mipmap.ic_launcher
        }
        fruit.type = fruit.type.capitalize()
    }


}
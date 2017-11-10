package brittany.varnom.com.fruitapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_fruit_details.*

class FruitDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_details)
        val fruit = intent.getParcelableExtra<GroceryData>("FRUIT_DATA")
        val fruitName = fruit.type
        val fruitPrice = fruit.price / 100
        val fruitWeight = fruit.weight
        val fruitImageUrl = fruit.imageurl

        val fruitDetails = "£$fruitPrice per ${fruitWeight}g."

        Picasso.with(this).load(fruitImageUrl).into(fruit_details_fruit_image)
        fruit_details_fruit_name.text = fruitName
        fruit_details.text = fruitDetails
    }
}

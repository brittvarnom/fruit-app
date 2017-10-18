package brittany.varnom.com.fruitapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fruit_details.*

class FruitDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_details)
        val fruit = intent.getParcelableExtra<FruitData>("FRUIT_DATA")
        val fruitName = fruit.type
        val fruitPrice = fruit.price / 100
        val fruitWeight = fruit.weight
        val fruitImage = fruit.imageRes

        val fruitDetails = "£$fruitPrice per ${fruitWeight}g."

        fruit_details_fruit_image.setImageResource(fruitImage)
        fruit_details_fruit_name.text = fruitName
        fruit_details.text = fruitDetails
    }
}

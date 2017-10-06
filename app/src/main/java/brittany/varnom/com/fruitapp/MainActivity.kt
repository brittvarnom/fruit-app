package brittany.varnom.com.fruitapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fruit = ArrayList<FruitData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
    }

    fun addToArray() {
        fruit.add(FruitData("Apple", 149.0, 120, R.drawable.img_apple))
        fruit.add(FruitData("Banana", 129.0, 80, R.drawable.img_banana))
        fruit.add(FruitData("Blueberry", 19.0, 18, R.drawable.img_blueberry))
        fruit.add(FruitData("Orange", 199.0, 150, R.drawable.img_orange))
        fruit.add(FruitData("Pear", 99.0, 100, R.drawable.img_pear))
        fruit.add(FruitData("Strawberry", 99.0, 20, R.drawable.img_strawberry))
        fruit.add(FruitData("Kumquat", 49.0, 80, R.drawable.img_kumquat))
        fruit.add(FruitData("Pitaya", 599.0, 100, R.drawable.img_pitaya))
        fruit.add(FruitData("Kiwi", 89.0, 200, R.drawable.img_kiwi))
    }

    private fun setUpRecyclerView() {
        if (fruit.isEmpty()) {
            addToArray()
        }

        val fruitAdapter = FruitAdapter(fruit)

        recycler_fruit_list.adapter = fruitAdapter
        recycler_fruit_list.layoutManager = LinearLayoutManager(applicationContext)
    }

}

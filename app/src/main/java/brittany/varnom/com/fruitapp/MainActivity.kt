package brittany.varnom.com.fruitapp

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var fruit = emptyList<FruitData>()
    private lateinit var fruitAdapter: FruitAdapter
    private val httpClient = OkHttpClient()
    private val ENDPOINT = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
    }

    private fun getJsonAsString(url: String,
                                success: ((body: String?) -> Unit),
                                failure: ((e: Exception?) -> Unit)) {
        val request = Request.Builder()
            .url(url)
            .build()

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                failure.invoke(e)
            }

            override fun onResponse(call: Call?, response: Response?) {
                success.invoke(response?.body()?.string())
            }
        })
    }

    private fun addToArray() {
        getJsonAsString(ENDPOINT, { fruitJson ->
            fruit = FruitTransformer.transform(fruitJson)
            val runnable = Runnable {
                fruitAdapter.updateFruit(fruit)
            }
            val handler = Handler(applicationContext.mainLooper)
            handler.post(runnable)

        }, { failure ->
            failure?.printStackTrace()
        })
    }

    private fun setUpRecyclerView() {
        fruitAdapter = FruitAdapter(fruit)
        if (fruit.isEmpty()) {
            addToArray()
        }

        recycler_fruit_list.adapter = fruitAdapter
        recycler_fruit_list.layoutManager = LinearLayoutManager(applicationContext)
    }

}
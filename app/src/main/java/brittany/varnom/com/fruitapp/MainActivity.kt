package brittany.varnom.com.fruitapp

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var groceries = emptyList<GroceryData>()
    private var spinnerObjects = emptyList<String>()

    //    var fruitObject = "groceries"
//    var vegObject = "vegetables"
    var selectedObjectName = "groceries"

    private lateinit var groceryAdapter: FruitAdapter
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private val httpClient = OkHttpClient()

    private val ENDPOINT = "https://fierce-ravine-28456.herokuapp.com"

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (selectedObjectName != spinner.selectedItem.toString()) {
            selectedObjectName = spinner.selectedItem.toString()
            Log.d(">>>>", selectedObjectName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner.onItemSelectedListener = this
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

    private fun addToArray(objectName: String) {
        getJsonAsString(ENDPOINT, { fruitJson ->
            spinnerObjects = GroceryTransformer.transformSpinnerObjects(fruitJson)
            groceries = GroceryTransformer.transform(fruitJson, "groceries")
            Log.d(">>>>", "$spinnerObjects $groceries")
            val runnable = Runnable {
                groceryAdapter.updateFruit(groceries)
                updateSpinner()
            }
            val handler = Handler(applicationContext.mainLooper)
            handler.post(runnable)

        }, { failure ->
            failure?.printStackTrace()
        })
    }

    private fun setUpRecyclerView() {
        groceryAdapter = FruitAdapter(groceries)
        addToArray(selectedObjectName)

        recycler_fruit_list.adapter = groceryAdapter
        recycler_fruit_list.layoutManager = LinearLayoutManager(applicationContext)

        spinnerAdapter = ArrayAdapter<String>(this, R.layout.custom_spinner)
        spinner.adapter = spinnerAdapter

    }

    //    private fun updateSpinner() {
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinnerAdapter.clear()
//        spinnerAdapter.add("All")
//        spinnerAdapter.addAll(spinnerObjects)
//        spinner.adapter = spinnerAdapter
//    }
    private fun updateSpinner() {
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAdapter.clear()
        val groceryTypes = ArrayList<String>(setOf())
        for (i in groceries)
            if (!groceryTypes.contains(i.kind.capitalize())) {
                groceryTypes.add(i.kind.capitalize())
            }
        spinnerAdapter.clear()
        spinnerAdapter.add("All")
        spinnerAdapter.addAll(groceryTypes)

        spinner.adapter = spinnerAdapter
    }

}
package brittany.varnom.com.fruitapp

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

object GroceryTransformer {

    fun transform(json: String?, objectName: String): List<GroceryData> {
        try {
            val groceryObject = JSONObject(json)

            val groceryArray = groceryObject.getJSONArray(objectName)

            val groceries = mutableListOf<GroceryData>()

            for (i in 0..(groceryArray.length() - 1)) {
                val groceryItem = groceryArray.getJSONObject(i)
                val grocery = GroceryData(
                    groceryItem.getString("type"),
                    groceryItem.getDouble("price"),
                    groceryItem.getInt("weight"),
                    groceryItem.getString("imageurl"),
                    groceryItem.getString("kind")
                )

                groceries.add(grocery)
            }
            return groceries
        } catch (e: JSONException) {
            e.printStackTrace()
            return emptyList()
        }
    }

    fun transformSpinnerObjects(json: String?): List<String> {
        val groceryDataObject = JSONObject(json)

        val spinnerObjects = (0..(groceryDataObject.length() -1)).map {
            groceryDataObject.names().getString(it).capitalize()
        }

        Log.d(">>>>", spinnerObjects.toString())
        return spinnerObjects
    }
}
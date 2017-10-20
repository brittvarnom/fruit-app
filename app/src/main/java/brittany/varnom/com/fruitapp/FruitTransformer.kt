package brittany.varnom.com.fruitapp

import org.json.JSONException
import org.json.JSONObject

object FruitTransformer {
    val OBJECTNAME = "vegetables"

    fun transform(json: String?): List<FruitData> {
        try {
            val fruitDataObject = JSONObject(json)

            val fruitArray = fruitDataObject.getJSONArray(OBJECTNAME)

            val fruits = mutableListOf<FruitData>()

            for (i in 0..(fruitArray.length() - 1)) {
                val fruitItem = fruitArray.getJSONObject(i)
                val fruit = FruitData(
                    fruitItem.getString("type"),
                    fruitItem.getDouble("price"),
                    fruitItem.getInt("weight"),
                    fruitItem.getString("imageurl")
                )

                fruits.add(fruit)
            }
            return fruits
        } catch (e: JSONException) {
            e.printStackTrace()
            return emptyList()
        }
    }
}
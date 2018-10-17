package com.example.itelisman.foodapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {
    var adapter:FoodAdapter?=null
    var listOfFoods = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load foods
        listOfFoods.add(Food("French Fries", "A delicious side", R.drawable.french_fries))
        listOfFoods.add(Food("Coffee", "A delicious drink", R.drawable.coffee_pot))
        listOfFoods.add(Food("Espresso", "A delicious drink", R.drawable.espresso))
        listOfFoods.add(Food("Sugar Cubes", "A delicious ingredient", R.drawable.sugar_cubes))
        listOfFoods.add(Food("Honey", "A delicious ingredient", R.drawable.honey))
        listOfFoods.add(Food("Ice Cream", "A delicious dessert", R.drawable.strawberry_ice_cream))
        adapter = FoodAdapter(this, listOfFoods)

        listFood.adapter = adapter
    }

    class FoodAdapter: BaseAdapter {
        var listOfFood = ArrayList<Food>()
        var context:Context?=null
        constructor(context: Context, listOfFood:ArrayList<Food>):super(){
            this.context = context
            this.listOfFood = listOfFood
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val food = listOfFood[position]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView = inflater.inflate(R.layout.food_ticket, null)
            foodView.imageViewFood.setImageResource(food.image!!)
            foodView.textViewFoodName.text = food.name!!
            foodView.foodTicketLayout.setOnClickListener(){
                //Toast.makeText(context, food.name, Toast.LENGTH_SHORT).show()
                val intent = Intent(context, FoodDetails::class.java)
                intent.putExtra("name", food.name!!)
                intent.putExtra("desc", food.desc!!)
                intent.putExtra("image", food.image!!)
                context!!.startActivity(intent)
            }


            return foodView
        }

        override fun getItem(position: Int): Any {
            return listOfFood[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfFood.size
        }

    }
}

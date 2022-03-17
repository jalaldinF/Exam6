package com.example.exam6modul.activties.list

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.exam6modul.R
import com.example.exam6modul.database.UserDB
import com.example.exam6modul.database.UserRepository
import com.example.exam6modul.model.Card
import com.example.exam6modul.networking.RetrofitHttp
import kotlinx.android.synthetic.main.activity_card_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardListActivity : AppCompatActivity() {

//    val TAG:String = MainActivity::class.java.simpleName
//    lateinit var textV: TextView
//    lateinit var textResult: TextView
//    lateinit var userList:ArrayList<Card>

    lateinit var card:Card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_add)

        initView()

    }

    private fun initView() {
        val name =  et_name.text.toString()
        val number = et_number.text.toString()

        card = Card(number.toInt(),name,"55/58")

        btn_add.setOnClickListener {

            if (isInternetAvailable()) {
                addCard(card)
            } else {
                addCardDB(card)
            }

        }
    }

    private fun addCardDB(card: Card) {
        val repository = UserRepository(application)
        repository.deleteUsers()


        val userDB = UserDB(card.id!!,card.card_number,card.owner_name,card.end_date)
        repository.saveUser(userDB)

        repository.saveUser(userDB)
        Log.d("@@@SAVED",repository.getUsers().toString())
    }



    private fun addCard(card: Card) {
        RetrofitHttp.userServise.createCards(card).enqueue(object :Callback<Card>{
            override fun onResponse(call: Call<Card>, response: Response<Card>) {


                if (response.body() != null){
                    Log.d("DONE","onResponse ${response.body().toString()}")
//
//                    userList.clear()
//                    userList.addAll(response.body()!!)
//                    textResult.text = response.body().toString()
//                    saveToDB(response.body()!!)
                }
                else{
                    Log.d("NULLL","null")
                    // Toast.makeText(this@CardListActivity, "Null", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Card>, t: Throwable) {
                Log.d("FAIL","Fail")
            }

        })

    }

    private fun isInternetAvailable(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return infoMobile!!.isConnected || infoWifi!!.isConnected

    }
}
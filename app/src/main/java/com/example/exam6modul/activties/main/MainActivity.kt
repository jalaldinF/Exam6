package com.example.exam6modul.activties.main

import android.app.ListActivity
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam6modul.R
import com.example.exam6modul.activties.list.CardListActivity
import com.example.exam6modul.adapter.CardAdapter
import com.example.exam6modul.database.UserDB
import com.example.exam6modul.database.UserRepository
import com.example.exam6modul.model.Card
import com.example.exam6modul.networking.RetrofitHttp
import com.example.exam6modul.networking.servise.UserServise
import kotlinx.android.synthetic.main.activity_card_add.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/*
1- Create card list page
2- Add credit card page
3- Mock Api as a backend
4- In case of Offline, use database
*/

class MainActivity : AppCompatActivity() {
/*
     lateinit var card:Car

    val TAG:String = MainActivity::class.java.simpleName
    lateinit var textV: TextView
    lateinit var textResult: TextView
    lateinit var cardList:ArrayList<Card>*/


    lateinit var recyclerView: RecyclerView
    lateinit var adapter : CardAdapter
    lateinit var list: ArrayList<Card>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        list = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(this)
        workWithRetrofit()
        val add_btn = findViewById<ImageView>(R.id.btn_add)
        add_btn.setOnClickListener {
            val intent = Intent(this,CardListActivity::class.java)
            startActivity(intent)
        }
    }

    fun workWithRetrofit(){
        RetrofitHttp.userServise.getAllCards().enqueue(object: Callback<ArrayList<Card>> {
            override fun onResponse(
                call: Call<ArrayList<Card>>,
                response: Response<ArrayList<Card>>
            ) {
                list.addAll(response.body()!!)
                adapter = CardAdapter(list)
                recyclerView.adapter = adapter
                Log.d("@@@",response.body().toString())
            }

            override fun onFailure(call: Call<ArrayList<Card>>, t: Throwable) {
                Log.e("@@@","Failure")
            }

        })
    }
}

     /*   initViews()

//        getUsers()





    val card = getUsers()
        cards = ArrayList()

    val adapter = CardAdapter(cards)
    initView(adapter)

    settings.setOnClickListener{
        val intent = Intent(this,SettubgsActivity::class.java)
        startActivity(intent)

    }
    chat_settings.setOnClickListener{
        val intent = Intent(this,ChatSettingsActivity::class.java)
        startActivity(intent)
    }

}

private fun initView(adapter: CardAdapter) {

    recyclerView.layoutManager = GridLayoutManager(this,1)
    recyclerView.adapter = adapter

}



    fun getUsersFromDB(){

        val repository = UserRepository(application)
        Log.d("TAG",repository.getUsers().toString())
       // textResult.text = repository.getUsers().toString()
    }


    fun initViews(){

        userList = java.util.ArrayList()
//        textV = findViewById(R.id.textV)
//        textResult = findViewById(R.id.textresult)

        if (isInternetAvailable()){

          //  textV.text = "Online"

            getUsers()

        }else{
          //  textV.text = "Offline"
            getUsersFromDB()
        }

    }

    private fun getUsers() {
        RetrofitHttp.userServise.getAllCards().enqueue(object : Callback<ArrayList<Card>> {
            override fun onResponse(
                call: Call<ArrayList<Card>>,
                response: Response<ArrayList<Card>>
            ) {


                if (response.body() != null){
                    Log.d(TAG,"onResponse ${response.body().toString()}")

                    cardList.clear()
                    cardList.addAll(response.body()!!)
                   // textResult.text = response.body().toString()
                    saveToDB(response.body()!!)
                }
                else{
                    Log.d(TAG,"null")
                    Toast.makeText(this@MainActivity, "Null", Toast.LENGTH_SHORT).show()
                }


            }

            override fun onFailure(call: Call<ArrayList<Card>>, t: Throwable) {
                Log.d(TAG,"Fail")
             //   textResult.text = t.localizedMessage
            }

        })
    }

    fun saveToDB(response: ArrayList<Card>){

        val repository = UserRepository(application)
        repository.deleteUsers()

        for (i in response){
            val userDB = UserDB(i.id!!,i.card_number,i.owner_name,i.end_date)
            repository.saveUser(userDB)
           // textResult.text = response.toString()
        }

    }

    private fun isInternetAvailable(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return infoMobile!!.isConnected || infoWifi!!.isConnected
    }
}*/
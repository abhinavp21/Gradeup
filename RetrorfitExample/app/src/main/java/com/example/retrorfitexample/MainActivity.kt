package com.example.retrorfitexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.ApiInterface
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.helper.AppHelper
import com.example.myapplication.models.CreateUserRequest
import com.example.myapplication.models.CreateUserResponse
import com.example.retrorfitexample.models.ListOfUser
import com.example.retrorfitexample.models.UserData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DataAdapter(var context: Context, var usersList: MutableList<UserData>): RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var avatarNameView = itemview.findViewById<TextView>(R.id.userName)
            var avatarEmailView = itemview.findViewById<TextView>(R.id.userEmail)
            var avatarImageView = itemview.findViewById<ImageView>(R.id.userImage)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int):  DataViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.user,parent,false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        var usersModel = usersList?.get(position)
        if (usersModel != null) {
            holder.avatarNameView.text = usersModel.firstName
        }
        if (usersModel != null) {
            holder.avatarEmailView.text = usersModel.email
        }
//        holder.avatarImageView.
//        val eachCard = holder.itemView
//        eachCard.setOnClickListener {
//            val intent = Intent(context, ParticularRestaurant::class.java)
//            intent.putExtra("name", restaurantmodel.name)
//            intent.putExtra("img", restaurantmodel.img)
//            context.startActivity(intent)
//        }
    }
    override fun getItemCount(): Int {
        return usersList.size
    }
}

data class data(var avatar:String, var email: String, val name: String)



class MainActivity : AppCompatActivity() {

    lateinit var txtData: TextView
    lateinit  var retrofit: Retrofit
    lateinit var apiInterface: ApiInterface
    var progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        /*Get Api Example*/
        getUsersList()

        //Post Api Example
        //createNewUser()
    }

    private fun initData() {
//        txtData = findViewById(R.id.txtData)
        progressBar = findViewById(R.id.progressBar)

        /*Creating the instance of retrofit */
        retrofit = RetrofitClient.getInstance()

        /*Get the reference of Api interface*/
        // we can call methods
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    private fun getUsersList() {
        /*Internet check*/
        if (AppHelper.isConnected(this)) {
            progressBar?.visibility = View.VISIBLE
//            <datatype>
            apiInterface.getUsersList("1").enqueue(object : Callback<ListOfUser?> {

                override fun onResponse(call: Call<ListOfUser?>, response: Response<ListOfUser?>) {
//                    TODO("Not yet implemented")
                    progressBar?.visibility = View.GONE
                    if (response?.isSuccessful!!) {
                        Log.v("SERVER_DATA",Gson().toJson(response.body()))

                        val myList = response.body()?.data
                        val users = mutableListOf<UserData>()
                        if(myList!=null){
                            users.addAll(myList!!)

                            var dataView =findViewById<RecyclerView>(R.id.listOfUsers)
                            dataView.layoutManager = LinearLayoutManager(this@MainActivity)
                            dataView.adapter = DataAdapter(this@MainActivity,users)

                        }

//                        txtData.text = Gson().toJson(response.body())
                    } else {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ListOfUser?>, t: Throwable) {
//                    TODO("Not yet implemented")
                    progressBar?.visibility = View.GONE

                    t.printStackTrace()
                }

            })
        }
        else{
            Toast.makeText(this@MainActivity, "Please check internet connection", Toast.LENGTH_LONG).show()

        }
    }
//            apiInterface.getSingleUser("1")?.enqueue(object :Callback<SingleUserResponse?> {
//                override fun onResponse(
//                    call: Call<SingleUserResponse?>,
//                    response: Response<SingleUserResponse?>
//                ) {
//                    /*Set you data to adapter here*/
//                    progressBar?.visibility = View.GONE
//
//                    if (response?.isSuccessful!!) {
//                        txtData.text = Gson().toJson(response.body())
//
//                    } else {
//                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<SingleUserResponse?>, t: Throwable) {
//                    progressBar?.visibility = View.GONE
//                    t.printStackTrace()
//
//                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG).show()
//                }
//
//            })
//        else
//        {
//            Toast.makeText(this@MainActivity, "Please check you internet connection", Toast.LENGTH_LONG).show()
//        }

    private fun createNewUser() {
        if(AppHelper.isConnected(this))
        {
            progressBar?.visibility = View.VISIBLE

            apiInterface.createUser(CreateUserRequest("abhishek", "Developer")).enqueue(object :
                Callback<CreateUserResponse> {
                override fun onResponse(call: Call<CreateUserResponse>,
                                        response: Response<CreateUserResponse>
                ) {
                    progressBar?.visibility = View.GONE

                    if (response.isSuccessful) {
                        txtData.setText(Gson().toJson(response.body()))
                    } else {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<CreateUserResponse>, t: Throwable) {
                    progressBar?.visibility = View.GONE

                    t.printStackTrace()
                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            })
        }
        else
        {
            Toast.makeText(this@MainActivity, "Please check you internet connection", Toast.LENGTH_LONG).show()
        }
    }
}
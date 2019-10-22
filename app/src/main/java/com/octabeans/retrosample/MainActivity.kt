package com.octabeans.retrosample

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.octabeans.retrosample.adapters.AdapterNote
import com.octabeans.retrosample.data.ResNote
import com.octabeans.retrosample.data.ResVendor
import com.octabeans.retrosample.retrofit.ApiClient
import com.octabeans.retrosample.retrofit.ApiService
import com.octabeans.retrosample.retrofit.User
import com.octabeans.retrosample.vendors.NewDataModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var mAdapter: AdapterNote
    lateinit var notesList: ArrayList<ResNote>
    lateinit var recyclerView: RecyclerView
    lateinit var apiService: ApiService

    var newsViewModel: NewDataModel? = null
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mContext = this

        recyclerView = findViewById(R.id.recycler_view)
        apiService = ApiClient.getClient(this).create(ApiService::class.java)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        notesList = ArrayList()
        mAdapter = AdapterNote(this, notesList)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.setItemAnimator(DefaultItemAnimator())
//        recyclerView.addItemDecoration(
//            MyDividerItemDecoration(
//                this,
//                LinearLayoutManager.VERTICAL,
//                16
//            )
//        )
        recyclerView.setAdapter(mAdapter)

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
//        recyclerView.addOnItemTouchListener(
//            RecyclerTouchListener(this,
//                recyclerView, object : RecyclerTouchListener.ClickListener() {
//                    fun onClick(view: View, position: Int) {}
//
//                    fun onLongClick(view: View, position: Int) {
//                        showActionsDialog(position)
//                    }
//                })
//        )

        /**
         * Check for stored Api Key in shared preferences
         * If not present, make api call to register the user
         * This will be executed when app is installed for the first time
         * or data is cleared from settings
         * */
//        if (TextUtils.isEmpty(PrefUtils.getApiKey(this))) {
//            registerUser()
//        } else {
            // user is already registered, fetch all notes
//            fetchAllNotes()
//        }
//        fetchAllVendors()

        newsViewModel = ViewModelProviders.of(this).get(NewDataModel::class.java)
        newsViewModel!!.init(mContext)
        newsViewModel!!.newsRepository.observe(this, androidx.lifecycle.Observer {
            val newsArticles = it.vendorResponseData?.vendors
            newsArticles?.let { it1 -> notesList.addAll(it1) }
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun registerUser() {
        // unique id to identify the device
        val uniqueId = UUID.randomUUID().toString()

        apiService.register(uniqueId).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.e("User",response.message())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    /**
     * Fetching all notes from api
     * The received items will be in random order
     * map() operator is used to sort the items in descending order by Id
     */
    private fun fetchAllNotes() {

        apiService.fetchAllNotes().enqueue(object : Callback<List<ResNote>> {
            override fun onFailure(call: Call<List<ResNote>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<ResNote>>, response: Response<List<ResNote>>) {
                if(response.isSuccessful) {
                    notesList.clear()
                    response.body()?.let { notesList.addAll(it) }
                    mAdapter.notifyDataSetChanged()
                }else{
                    Log.e("Get All",response.message())
                }

//                toggleEmptyNotes()
            }
        })
    }

    private fun fetchAllVendors(){
        apiService.fetchAllVendors().enqueue(object: Callback<ResVendor>{
            override fun onFailure(call: Call<ResVendor>, t: Throwable) {
                Toast.makeText(applicationContext,"CALLED FAILURE",Toast.LENGTH_SHORT).show()
                Log.e("Failure","Vendors"+t.message)
            }

            override fun onResponse(
                call: Call<ResVendor>,
                response: Response<ResVendor>
            ) {
                Toast.makeText(applicationContext,"CALLED SUCCESS",Toast.LENGTH_SHORT).show()
                Log.e("Success",response.message())
                if(response.isSuccessful) {
                    notesList.clear()
                    response.body()?.vendorResponseData?.vendors?.let { notesList.addAll(it) }
                    mAdapter.notifyDataSetChanged()
                }
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

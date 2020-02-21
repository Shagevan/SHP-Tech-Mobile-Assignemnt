package com.sphtech.mobile.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sphtech.mobile.R
import com.sphtech.mobile.api.MobileDataApi
import com.sphtech.mobile.repository.MobileDataRepository
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var factory: MobileDataViewModelFactory
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        checkInternetConnection()
    }


    fun fetchData(){

        val api = MobileDataApi()

        val repository =
            MobileDataRepository(api)

        factory = MobileDataViewModelFactory(
            repository
        )

        viewModel = ViewModelProviders.of(this,factory).get(HomeViewModel::class.java)

        viewModel.getMobileData()

        viewModel.mobileData.observe(this, Observer { mobileDatas ->

            recyclerView.also{
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter =
                    MobileDataAdapter(
                        mobileDatas
                    )
            }
        })
    }


    fun checkInternetConnection() : Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return if(isConnected){
            fetchData()
            true
        } else{
            showConnectInternetDialog()
            false
        }

    }


    fun showConnectInternetDialog(){

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Please connect with internet connection")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id -> dialog.dismiss()
                checkInternetConnection()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("No Internet connection")
        alert.show()

    }
}

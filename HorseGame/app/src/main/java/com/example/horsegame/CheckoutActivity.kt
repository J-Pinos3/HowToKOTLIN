package com.example.horsegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class CheckoutActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "CheckoutActivity"
        private const val BACKEND_URL = "http://10.0.2.2:4242"
    }

    private lateinit var paymentIntentClientSecret: String
    private lateinit var paymentSheet: PaymentSheet
    private lateinit var payButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        payButton = findViewById(R.id.pay_button)
        payButton.setOnClickListener(::onPayClicked)
        payButton.isEnabled = false
        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        fetchPaymentIntent()
    }


    private fun fetchPaymentIntent(){
        val url = "$BACKEND_URL/create-payment-intent"

        val amount = 100.0f
        val payMap: MutableMap<String, Any> = HashMap()
        val itemMap: MutableMap<String, Any> = HashMap()
        val itemList: MutableList<Map<String, Any>> = ArrayList()

        payMap["currency"] = "usd"
        itemMap["id"] = "photo_suscription"
        itemMap["amount"] = amount
        itemList.add(itemMap)
        payMap["items"] = itemList

        val shoppingCartContent = Gson().toJson(payMap)

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = shoppingCartContent.toRequestBody(mediaType)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        OkHttpClient()
            .newCall(request)
            .enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    showAlert("Failed to load data", "Error: $e")
                }

                override fun onResponse(call: Call, response: Response) {
                    if(!response.isSuccessful){
                        showAlert("Failed to load page", "Error: $response")
                    }else{
                        val responseData = response.body?.string()
                        val responseJson = responseData?.let { JSONObject(it) } ?: JSONObject()

                        paymentIntentClientSecret = responseJson.getString("clientSecret")
                        runOnUiThread { payButton.isEnabled = true }
                    }
                }
            })
    }



    private fun showAlert(title: String, message: String? = null) {
        runOnUiThread {
            val builder = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
            builder.setPositiveButton("Ok",null)
            builder.create().show()
        }
    }


    private fun showToast(message:String){
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun onPayClicked(view: View){
        val configuration = PaymentSheet.Configuration("Example, Inc.")
        paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret, configuration)
    }

    private fun onPaymentSheetResult(paymentResult: PaymentSheetResult){
        when(paymentResult){
            is PaymentSheetResult.Completed ->{
                showToast("Payment Complete!")
            }

            is PaymentSheetResult.Canceled ->{
                showToast("Payment Canceled!")
            }

            is PaymentSheetResult.Failed ->{
                showAlert("Payment Failed", paymentResult.error.localizedMessage)
            }
        }
    }

}
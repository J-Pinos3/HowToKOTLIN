package com.reverb.wildrunning

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.log
import kotlin.properties.Delegates

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider


class LoginActivity : AppCompatActivity() {

    companion object{
        lateinit var userEmail: String
        lateinit var providerSession: String
    }

    private var email by Delegates.notNull<String>()
    private var password by Delegates.notNull<String>()
    private lateinit var etEmail: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etPassword: EditText
    private lateinit var lyTareas: LinearLayout

    private lateinit var mAuth: FirebaseAuth

    private var RESULT_CODE_GOOGLE_SIGN_IN = 100

    private var callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initUI()
        lyTareas.visibility = View.INVISIBLE
        etConfirmPassword.visibility = View.INVISIBLE

        manageButtonLogin()
        etEmail.doOnTextChanged {  text, start,  before, count ->  manageButtonLogin() }
        etPassword.doOnTextChanged {  text, start,  before, count ->  manageButtonLogin() }
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null){
            goHome(currentUser.email.toString(), currentUser.providerId)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        startActivity(startMain)
    }

    private fun manageButtonLogin(){
        var btnLogin = findViewById<TextView>(R.id.tvLogin)
        email = etEmail.text.toString()
        password = etPassword.text.toString()

        if( TextUtils.isEmpty(password) || !ValidateEmail.isEmail(email) ){
            btnLogin.setBackgroundColor(ContextCompat.getColor(this,R.color.gray))
            btnLogin.isEnabled = false

        }else{
            btnLogin.setBackgroundColor(ContextCompat.getColor(this,R.color.green))
            btnLogin.isEnabled = true

        }
    }

    private fun initUI(){
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        lyTareas = findViewById(R.id.lyTareas)
        mAuth = FirebaseAuth.getInstance()
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        //TODO add ly terms
    }

    fun login(view: View){
        loginUser()
    }

    private fun loginUser(){
        email =  etEmail.text.toString()
        password = etPassword.text.toString()

        var confirmedPassword: String = etConfirmPassword.text.toString()


        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                 goHome(email, "email")

                }else{
                    if(lyTareas.visibility == View.INVISIBLE){
                        lyTareas.visibility = View.VISIBLE
                        etConfirmPassword.visibility = View.VISIBLE

                    }else{
                        var cbxAccept = findViewById<CheckBox>(R.id.cbAccept)
                        if( cbxAccept.isChecked && ValidatePassword.areSamePasswords(password, confirmedPassword) ){
                            register()
                        }else{
                            Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }


    private fun goHome(email:String, provider:String){

        userEmail = email
        providerSession = provider//email, google, etc

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    private fun register(){
        email =  etEmail.text.toString()
        password = etPassword.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    //save into db
                    var dateRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())

                    var dbRegister = FirebaseFirestore.getInstance()


                    dbRegister.collection("user").document(email)
                        .set(hashMapOf(
                            "user" to email,
                            "dateRegister" to dateRegister
                        ))

                    goHome(email, "email")
                }
                else Toast.makeText(this, "Error, algo ha salido muy mal", Toast.LENGTH_SHORT).show()

            }
    }


    fun goTerms(v: View){
        val intent = Intent(this, TermsActivity::class.java)
        startActivity(intent)
    }

    fun forgotPassword(v: View){
        //startActivity(Intent(this, ForgotPasswordActivity::class.java)) alternativa
        resetPassword()
    }

    private fun resetPassword(){
        var e = etEmail.text.toString()

        if(!TextUtils.isEmpty(e)){
            mAuth.sendPasswordResetEmail(e)
                .addOnCompleteListener {task->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Email enviado a $e", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"No se encontró a un usuario con ese corrreo", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this,"Indica un email ", Toast.LENGTH_SHORT).show()
        }
    }

    fun callSignInGoogle(view: View){
        signInGoogle()
    }

    private fun signInGoogle() {
        val gso  = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        var googleSigninClient = GoogleSignIn.getClient(this, gso)
        googleSigninClient.signOut()//en caso de que ya haya una sesión abierta previamente

        val signInIntent = googleSigninClient.signInIntent
        startActivityForResult(signInIntent, RESULT_CODE_GOOGLE_SIGN_IN)
    }

    fun callSignInFacebook(view: View){
        signInFacebook()
    }

    private fun signInFacebook(){
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    loginResult.let {
                        val token = it.accessToken
                        val credentials  =FacebookAuthProvider.getCredential(token.token)

                        // ADDED 16/02/2025
                        var dateRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())
                        var dbRegister = FirebaseFirestore.getInstance()
                        dbRegister.collection("user").document(mAuth.uid!!)
                            .addSnapshotListener { value, error ->
                                val logedUserFacebookMail = value.toString()
                                Toast.makeText(this@LoginActivity,"Facebook Mail: $logedUserFacebookMail", Toast.LENGTH_SHORT).show()
                            }

                        mAuth.signInWithCredential(credentials).addOnCompleteListener {
                            if(it.isSuccessful){

                                email = it.result.user?.email.toString()
                                /** CHECK IF THE USER ALREADY EXISTS, WORKING CODE ABOVE
                                dbRegister.collection("user").document(mAuth.uid!!)
                                    .addSnapshotListener { value, error ->
                                        val mail = value.toString()
                                        Toast.makeText(this@LoginActivity,"Facebook Mail: $mail", Toast.LENGTH_SHORT).show()
                                    }
                                    dbRegister.collection("user").document(email) SAVE INTO DATABASE
                                    .set(hashMapOf(
                                        "user" to email,
                                        "dateRegister" to dateRegister
                                    ))
                                */
                                goHome(email, "Facebook")
                            }else
                                showError("Facebook")

                        }

                    }
                    //handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException) {
                    showError("Facebook")

                }
            },
        )
    }

    private fun showError(provider: String){
        Toast.makeText(this,"Error al logearse con Facebook", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        callbackManager.onActivityResult(requestCode, resultCode, data)


        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RESULT_CODE_GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)

                if(account != null){

                    email = account.email!!

                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    mAuth.signInWithCredential(credential).addOnCompleteListener {
                        if(it.isSuccessful){


                            goHome(email,"Google")
                        }else{
                            showError("Google")
                        }
                    }
                }
            }catch (e: ApiException){
                showError("Google")
            }
        }

    }

}








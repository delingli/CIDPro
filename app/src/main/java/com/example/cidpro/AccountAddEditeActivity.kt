package com.example.cidpro

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cidpro.UrlUtil.EDITUSERPASSWORD
import com.example.cidpro.UrlUtil.adduser
import com.example.cidpro.bean.AccountData
import com.example.cidpro.callback.DialogCallback
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import okhttp3.MediaType
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus


//账户添加,更新
open class AccountAddEditeActivity : AppCompatActivity() {
    //1 新增 ，2修改编辑
    var type: Int? = 1
    var idz: Int = -1
    override fun onDestroy() {
        super.onDestroy()
        OkGo.getInstance().cancelTag(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        setContentView(R.layout.activity_add_edite_account)
        val tv_save = findViewById<TextView>(R.id.tv_save)
        val tv_add_edite = findViewById<TextView>(R.id.tv_add_edite)
        val ll_username = findViewById<LinearLayout>(R.id.ll_username)
        val ll_loginName = findViewById<LinearLayout>(R.id.ll_loginName)
        val ll_phone = findViewById<LinearLayout>(R.id.ll_phone)
        val ll_password = findViewById<LinearLayout>(R.id.ll_password)
        val et_password = findViewById<EditText>(R.id.et_password)
        val et_userName = findViewById<EditText>(R.id.et_userName)
        val et_loginName = findViewById<EditText>(R.id.et_loginName)
        val et_phone = findViewById<EditText>(R.id.et_phone)
        tv_save.setOnClickListener {
            if (type == 1) {
                if (et_userName.text.toString().isNullOrEmpty()) {
                    Toast.makeText(this, "请输入正确用户名", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (et_loginName.text.toString().isNullOrEmpty()) {
                    Toast.makeText(this, "请输入正确登陆名", Toast.LENGTH_SHORT).show()

                    return@setOnClickListener
                }
                if (et_phone.text.toString().isNullOrEmpty()) {
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show()

                    return@setOnClickListener
                }
                if (et_password.text.toString().isNullOrEmpty()) {
                    Toast.makeText(this, "请输入正确密码", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                var params:HashMap<String,String> = HashMap()

                params.run {
                    this.put("id", "0")
                    this.put("userType", "0")
                    this.put("userName", et_userName.text.toString().trim())
                    this.put("loginName", et_loginName.text.toString().trim())
                    this.put("password", et_password.text.toString().trim())
                    this.put("phone", et_phone.text.toString().trim())
                }
                val json = Gson().toJson(params)
                val JSONS = MediaType.parse("application/json; charset=utf-8")
                val body = RequestBody.create(JSONS, json.toString())
                OkGo.post<AccountData>(adduser).tag(this)
                    .upRequestBody(body)
//                    .params("id", 0)
//                    .params("userType", 0)
//                    .params("userName",  et_userName.text.toString().trim())
//                    .params("loginName",  et_loginName.text.toString().trim())
//                    .params("password",  et_password.text.toString().trim())
//                    .params("phone",  et_phone.text.toString().trim())
                    .params(params)
                    .execute(object :
                        DialogCallback<AccountData?>(this) {
                        override fun onSuccess(response: Response<AccountData?>?) {
                            response?.let {
                                if (it.body()?.result?.code == 0) {
                                    Toast.makeText(
                                        this@AccountAddEditeActivity,
                                        "添加成功",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    EventBus.getDefault().post(MessageEvent())
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@AccountAddEditeActivity,
                                        it.body()?.result?.msg,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }

                        override fun onError(response: Response<AccountData?>?) {
                            super.onError(response)
                        }
                    })

            } else {
                if (!et_password.text.toString().isNullOrEmpty()) {
                    if (idz != -1) {
                        //todo 去更改
                        val params = HttpParams()
                        params.run {
                            this.put("id", idz)
                            this.put("password", et_password.text.toString())
                        }
                        OkGo.get<AccountData>(EDITUSERPASSWORD).tag(this)
                            .params(params)
                            .execute(object :
                                DialogCallback<AccountData?>(this) {

                                override fun onSuccess(response: Response<AccountData?>?) {
                                    response?.let {
                                        if (it.body()?.result?.code== 0) {
                                            Toast.makeText(
                                                this@AccountAddEditeActivity,
                                                "更改成功",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            EventBus.getDefault().post(MessageEvent())
                                            finish()
                                        } else {
                                            Toast.makeText(
                                                this@AccountAddEditeActivity,
                                                it.body()?.result?.msg,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            })
                    }
                    EventBus.getDefault().post(MessageEvent())

                } else {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show()
                }



            }

        }
        if (intent != null) {
            type = intent.getIntExtra("type", 1)
            idz = intent.getIntExtra("id", -1)
            if (type == 1) {
                tv_add_edite.setText("新增用户")
                ll_username.visibility = View.VISIBLE
                ll_loginName.visibility = View.VISIBLE
                ll_phone.visibility = View.VISIBLE
                ll_password.visibility = View.VISIBLE
            } else {
                tv_add_edite.setText("编辑用户")
                ll_username.visibility = View.GONE
                ll_loginName.visibility = View.GONE
                ll_phone.visibility = View.GONE
                ll_password.visibility = View.VISIBLE
            }

        }
    }
    open fun Btn_Back(view: View?) {
        finish()
    }

}
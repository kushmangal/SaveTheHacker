package kmzenon.savethehacker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private static final String TAG = "Login";
    private static final int REQUEST_SIGNUP = 0;
    private ProgressDialog progressDialog;

    EditText email_et, pass_et;
    Button signin_btn;
    TextView signup_link;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email_et = (EditText) findViewById(R.id.input_email);
        pass_et = (EditText) findViewById(R.id.input_password);
        signin_btn = (Button) findViewById(R.id.btn_login);
        signup_link = (TextView) findViewById(R.id.link_signup);

        signin_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = email_et.getText().toString();
                String pass = pass_et.getText().toString();
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(pass))
                    Toast.makeText(getApplicationContext(), "Please enter the credentials", Toast.LENGTH_SHORT).show();
                else {
                    progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Signing in...");
                    progressDialog.show();
                        validate(email,pass);
                    //Login

                }

            }
        });

        signup_link.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), CropActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }
    String mypass;String checkpass,id;
    public void validate(String email , String pass)
    {
        mypass=pass;
        StringRequest stringRequest = new StringRequest("http://savethe.pe.hu/getUser.php?email="+email, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");
                    JSONObject crop_json = result.getJSONObject(0);
                    checkpass=crop_json.getString("password");
                    id=crop_json.getString("id");
                    Log.d(TAG,result.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(mypass.equals(checkpass)) {
                    progressDialog.dismiss();
                    Intent intent1 = new Intent(getApplicationContext(), CropActivity.class);
                    SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("ID",id);
                    editor.putBoolean("ISGUEST",false);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
//                    intent1.putExtra("comid",comid);
//                    intent1.putExtra("guest","");
                    startActivity(intent1);
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(mycontext,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
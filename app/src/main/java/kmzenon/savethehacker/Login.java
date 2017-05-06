package kmzenon.savethehacker;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Login extends AppCompatActivity {

    private static final String TAG = "Login";
    private static final int REQUEST_SIGNUP = 0;

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

                    //Login

                }

            }
        });

        signup_link.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }
}
package nescoffee.taskk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {


    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    EditText etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail=(EditText) findViewById(R.id.etPassword1);
        final EditText etPassword=(EditText) findViewById(R.id.etEmail2);
        final String usr=etEmail.getText().toString();
        final String psw=etPassword.getText().toString();
        final Button bLogin = (Button)findViewById(R.id.bLogin);
        final Button bReg = (Button)findViewById(R.id.bReg);


        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
                                      }});

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref =getPreferences(Context.MODE_PRIVATE);
                String eerock = sharedPref.getString(usr, "no");

                if (eerock.equals(md5(psw))) {
                    Intent areaIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                    areaIntent.putExtra("email", usr);
                    startActivity(areaIntent);

                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Неверный пароль или логин!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }
        });


    }
}

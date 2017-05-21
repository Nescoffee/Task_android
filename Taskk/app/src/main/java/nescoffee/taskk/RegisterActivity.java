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

public class RegisterActivity extends AppCompatActivity {


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
    EditText etEmail, etPassword, etRePassword;
    Button bRegister;
    String usr,psw,repsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etEmail=(EditText) findViewById(R.id.etPassword1);
        etPassword=(EditText) findViewById(R.id.etEmail2);
        etRePassword=(EditText) findViewById(R.id.etRePassword);
        bRegister = (Button)findViewById(R.id.bRegister);
        usr=RegisterActivity.this.etEmail.getText().toString();
        psw=RegisterActivity.this.etPassword.getText().toString();
        repsw=RegisterActivity.this.etRePassword.getText().toString();
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!psw.equals(etRePassword.getText().toString()))
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Пароли не совпадают!" ,
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }
                else {
                    if (usr.length() > 6 && psw.length()>4 ) {
                        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                        String checked = sharedPref.getString(usr, "thereisin");
                        if (!checked.equals("thereisin")) {
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(usr, md5(psw));
                            editor.commit();

                            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            RegisterActivity.this.startActivity(loginIntent);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Пользователь с таким email-ом уже существует",
                                    Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Пароль должен быть больше 4 символов, а email больше 6",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });


    }
}

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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


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
    EditText etEmail,etPassword;
    Button bLogin,bReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail=(EditText) findViewById(R.id.etEmail2);
        etPassword=(EditText) findViewById(R.id.etPassword1);

        bLogin = (Button)findViewById(R.id.bLogin);
        bReg = (Button)findViewById(R.id.bReg);


        bReg.setOnClickListener(this);
        bLogin.setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bReg:
                reg();
                break;
            case R.id.bLogin:
                log();
                break;
            default:
                break;}}

    public void reg() {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(registerIntent);
    }
    public static final String APP_PREFERENCES = "mysettings";
    public void log() {
        String usr=etEmail.getText().toString();
        String psw=etPassword.getText().toString();
        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String eerock = sharedPref.getString(usr, "no");
        if(sharedPref.contains(usr)) {
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "Такой логин есть " + eerock,
//                    Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();

            if (eerock.equals(md5(psw))) {
                Intent areaIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                areaIntent.putExtra("email", usr);
                startActivity(areaIntent);

            } else {
                Toast toast1 = Toast.makeText(getApplicationContext(),
                        "Неверный пароль или логин!" + eerock,
                        Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
            }


        }

    }


}

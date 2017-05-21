package nescoffee.taskk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        final Button bExit = (Button)findViewById(R.id.bExit);
        final TextView tvView = (TextView) findViewById(R.id.tvView);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        tvView.setText("Your email is: " + email);
        bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(UserAreaActivity.this, LoginActivity.class);
                UserAreaActivity.this.startActivity(loginIntent);
            }
        });
    }
}

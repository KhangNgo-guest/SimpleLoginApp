package khangngo.ueh.edu.simplelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button submit;
    UserData userData;

    Button.OnClickListener onSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText usernameView = (EditText)findViewById(R.id.username);
            String username = usernameView.getText().toString();
            EditText passwordView = (EditText) findViewById(R.id.password);
            String password = passwordView.getText().toString();

            boolean result = userData.checkLogin(username, password);
            if (result){
                Toast.makeText(getApplicationContext(), "Login success!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userData = new UserData(getApplicationContext());
        userData.getUsersDataFromDB();

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(onSubmit);
    }
}
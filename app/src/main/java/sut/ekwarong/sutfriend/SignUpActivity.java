package sut.ekwarong.sutfriend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    // Explicit
    private EditText nameEditText, addressEditText, phoneEditText, userEditText, passwordEditText;
    private String nameString, addressString, phoneString, userString, passwordString, genderString, imageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText3);
        addressEditText = (EditText) findViewById(R.id.editText4);
        phoneEditText = (EditText) findViewById(R.id.editText5);
        userEditText = (EditText) findViewById(R.id.editText6);
        passwordEditText = (EditText) findViewById(R.id.editText7);
    }   // Main Method

    public void clickSignUpSign(View view) {

        // Get Value From EditText
        nameString = nameEditText.getText().toString().trim();
        addressString = addressEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

    }   // clickSign

}   // Main Class

package sut.ekwarong.sutfriend;

import android.content.Context;
import android.content.Intent;
import android.content.SyncContext;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {

    // Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Widget
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);

    }   // Main Method

    private class SynchronizeData extends AsyncTask<Void, Void, String> {

        // Explicit
        private Context context;
        private static final String urlJSON = "http://swiftcodingthai.com/Sut/get_data_master.php";


        public SynchronizeData(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();

                return response.body().string();

            } catch (Exception e) {

                Log.d("SutFriendV3", "e doInBack ==>" + e.toString());

                return null;
            }
        }   // doInBackground

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            Log.d("SutFriendV3", "JSON ==>" + s);

        }   // onPostExecute
    }   // SynchronizeData Class


    public void clickSignIn(View view) {

        // Get Value From EditText
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        // Check for Alert
        if (userString.equals("") || passwordString.equals("")) {

            // Check Space
            MyAlert myAlert = new MyAlert(this, R.drawable.rat48,
                    "Ivalid Value", "Fill All Box");
            myAlert.myDialog();
        } else {

            // No Space
            SynchronizeData synchronizeData = new SynchronizeData(this);
            synchronizeData.execute();
        }

    }   // clickSignIn

    // Get event from click button
    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }


}   // Main Class

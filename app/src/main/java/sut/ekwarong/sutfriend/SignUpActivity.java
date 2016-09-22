package sut.ekwarong.sutfriend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {

    // Explicit
    private EditText nameEditText, addressEditText, phoneEditText, userEditText, passwordEditText;
    private String nameString, addressString, phoneString, userString, passwordString,
            genderString, imageString, imagePathString, imageNameString;
    private RadioButton maleRadioButton, femaleRadioButton;
    private ImageView imageView;
    private boolean statusABoolean = true;
    private RadioGroup radioGroup;

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
        maleRadioButton = (RadioButton) findViewById(R.id.radioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.radioButton2);
        imageView = (ImageView) findViewById(R.id.imageView);
        radioGroup = (RadioGroup) findViewById(R.id.ragGender);

        // ImageView Controller
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");                                  // Open Gallery all image
                startActivityForResult(Intent.createChooser(intent, "Choose Image"), 1);
            }   // On Click
        });

        // Radio Controller
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton:
                        genderString = "Male";
                        break;
                    case R.id.radioButton2:
                        genderString = "Female";
                        break;
                }

            }   // On Check
        });

    }   // Main Method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {     // Do after ActivityResult
        super.onActivityResult(requestCode, resultCode, data);

        // Debugging
        if ((requestCode == 1) && (resultCode == RESULT_OK)) {
            Log.d("SutFriendV1", "Result ==> Success");

            // Find Path of Image
            Uri uri = data.getData();                   // Get data to uri
            imagePathString = myFindPath(uri);          // Call Method myFindPath then return to imagePathString
            Log.d("SutFriendV1", "imagePathString ==> " + imagePathString);

            // Setup imageView
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);                       // Set image to imageView

            } catch (Exception e) {
                e.printStackTrace();
            }
            statusABoolean = false;
        }   // if
    }   // OnActivityResult

    private String myFindPath(Uri uri) {
        String strResult = null;
        String[] strings = {MediaStore.Images.Media.DATA};                              // Declare String Array
        Cursor cursor = getContentResolver().query(uri, strings, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            strResult = cursor.getString(index);
        } else {
            strResult = uri.getPath();
        }
        return strResult;
    }

    public void clickSignUpSign(View view) {

        // Get Value From EditText
        nameString = nameEditText.getText().toString().trim();
        addressString = addressEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        // Check for Alter
        if (nameString.equals("") || addressString.equals("") ||
                phoneString.equals("") || userString.equals("") || passwordString.equals("")) {
            // Check Space
            MyAlert myAlert = new MyAlert(this, R.drawable.bird48, "Invalid Value", "Fill All Box");
            myAlert.myDialog();
        } else if (!(maleRadioButton.isChecked() || femaleRadioButton.isChecked())) {
            // None Check Gender
            MyAlert myAlert = new MyAlert(this, R.drawable.rat48, "No gender", "Please Check One");
            myAlert.myDialog();
        } else if (statusABoolean) {
            //None Chose Image
            MyAlert myAlert = new MyAlert(this, R.drawable.nobita48, "No Image", "Please Choose Image");
            myAlert.myDialog();
        } else {
            confirmData();
        }
    }   // clickSign

    private void confirmData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.kon48);
        builder.setTitle("Check Your Information");
        builder.setMessage("Name = " + nameString +
                "\nAddress = " + addressString +
                "\nPhone = " + phoneString +
                "\nGender = " + genderString);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }   // ConfirmData

}   // Main Class

package sut.ekwarong.sutfriend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by TEW on 9/21/2016.
 */
public class MyAlert {

    // Explicit
    private Context context;
    private int anInt;
    private String titleString, messageString;

    // Declare Construct variable MyAlert
    public MyAlert(Context context, int anInt, String titleString, String messageString) {
        this.context = context;                                             // Create context
        this.anInt = anInt;                                                 // Create icon
        this.titleString = titleString;                                     // Create title
        this.messageString = messageString;                                 // Create message
    }   // Declare 3 Parameters in Dialog - Icon, Title, Message

    public void myDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);     // Set builder to AlertDialog.Builder
        builder.setCancelable(false);                                       // Block undo button
        builder.setIcon(anInt);                                             // Set icon to dialog
        builder.setTitle(titleString);                                      // Set title to dialog
        builder.setMessage(messageString);                                  // Set message to dialog
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }   // Click to close dialog
        }); // Set Positive Button
        builder.show();                                                     // Show dialog
    }
}   // Main Class

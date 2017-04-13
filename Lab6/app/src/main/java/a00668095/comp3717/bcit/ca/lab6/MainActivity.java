package a00668095.comp3717.bcit.ca.lab6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
    EditText fname;
    EditText lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname = (EditText) findViewById(R.id.fnameField);
        lname = (EditText) findViewById(R.id.lnameField);
    }

    public void showAlert(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        // set title
        alertDialogBuilder.setTitle("Headding");

        // set dialog message
        alertDialogBuilder.setMessage("fname : "+fname.getText() + "\n" + "lname : "+lname.getText() )
                .setCancelable(false)
                .setPositiveButton("buttonname", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }


}

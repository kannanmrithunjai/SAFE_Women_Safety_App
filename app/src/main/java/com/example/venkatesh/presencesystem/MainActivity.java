package com.example.venkatesh.presencesystem;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;

public class MainActivity extends AppCompatActivity {

    Button btnLogin,btnspedial,btnmsg;

    private final static int LOGIN_PERMISSION=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button)findViewById(R.id.btnSignIn);

        btnmsg =(Button)findViewById(R.id.btnmsg);
        btnspedial = (Button)findViewById(R.id.btnspddial);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivityForResult(
                      AuthUI.getInstance().createSignInIntentBuilder().setAllowNewEmailAccounts(true).build(),LOGIN_PERMISSION
              );
            }
        });

        btnmsg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMS();
            }
        });


        btnspedial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:7358706268"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(callIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LOGIN_PERMISSION)
        {
            startNewActivity(resultCode,data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startNewActivity(int resultCode, Intent data) {
        if(resultCode == RESULT_OK)
        {
         Intent intent = new Intent(MainActivity.this,ListOnline.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this, "Login Failed...!!!", Toast.LENGTH_SHORT).show();
        }
    }


    protected void sendSMS() {
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String ("8778456882"));
        smsIntent.putExtra("sms_body"  , "Hey..I am feeling Unsafe.. Track Me!!");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}

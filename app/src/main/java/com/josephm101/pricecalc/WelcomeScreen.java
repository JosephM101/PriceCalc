package com.josephm101.pricecalc;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class WelcomeScreen extends AppCompatActivity {

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        getSupportActionBar().hide();
        ActivityResultLauncher<String> requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {

                    } else {

                    }
                });

        Button button_getStarted = findViewById(R.id.button_getStarted);
        button_getStarted.setOnClickListener(v -> {
            int PERMISSION_ALL = 1;
            String[] PERMISSIONS = {
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            Activity act = this;
            if (!hasPermissions(this, PERMISSIONS)) {
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this)
                        .setTitle("Need permissions")
                        .setMessage("PriceCalc needs some permissions to ensure that it works properly. If any need to be granted, they will be requested after you click OK.")
                        .setPositiveButton("OK", (dialog, which) -> {
                            ActivityCompat.requestPermissions(act, PERMISSIONS, PERMISSION_ALL);
                            SharedPreferences settings1 = getApplicationContext().getSharedPreferences(Preferences.WelcomeScreen.PreferenceGroup, 0);
                            SharedPreferences.Editor editor = settings1.edit();
                            editor.putInt(Preferences.WelcomeScreen.ENTRY_SHOWN, 1);
                            editor.apply();
                            dialog.dismiss();
                            finish();
                        });
                materialAlertDialogBuilder.show();
            }
        });
    }
}
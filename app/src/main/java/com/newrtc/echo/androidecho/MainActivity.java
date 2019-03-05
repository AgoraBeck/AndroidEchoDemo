package com.newrtc.echo.androidecho;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.WindowManager;

import java.util.Arrays;


public class MainActivity extends Activity {

    AudioAec m_audio = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setSpeakerphoneOn(true);


        String[] needPermissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};
        AppUtil.checkAndRequestAppPermission(this, needPermissions, ConstantApp.PERMISSION_REQUEST_CODE);


        m_audio = new AudioAec();
        m_audio.StartRecorderAndPlayer();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != ConstantApp.PERMISSION_REQUEST_CODE)
            return;

        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED)
                finish();
        }
    }
}
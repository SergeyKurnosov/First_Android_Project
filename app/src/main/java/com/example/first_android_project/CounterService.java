package com.example.first_android_project;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CounterService extends Service  {

     Handler handler;
     Runnable runnable;
     int counter = 0;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SVC", "create  "+counter);
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                counter++;
                Log.d("SVC", "Тик  "+counter);
                handler.postDelayed(this, 1000);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SVC", "start  "+counter);
        handler.post(runnable);
        Log.d("SVC", "Тик  "+counter);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        Log.d("SVC", "Тик  "+counter);
        super.onDestroy();
    }
}

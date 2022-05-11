package com.emre.amiralbatti;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView time, time2;
    Button devam,dur;
    int sayac =0;
    CountDownTimer cdt;
    Runnable r;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.time);
        time2 = findViewById(R.id.time2);
        devam = findViewById(R.id.devam);
        devam.setEnabled(false);
        dur = findViewById(R.id.dur);
        cdt = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                time.setText("zaman:"+l/1000);
               // time2.setText("kronometre:"+sayac++);
            }

            @Override
            public void onFinish() {
                start();
            }
        }.start();
        dur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setMessage("ciddi misin ?")
                        .setTitle("sayaçlar durdurulacak!")
                        .setPositiveButton("evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cdt.cancel();
                                handler.removeCallbacks(r);
                                devam.setEnabled(true);
                                dur.setEnabled(false);
                            }
                        })
                        .setNegativeButton("hayır",null);
                builder.show();
            }
        });
        devam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdt.start();
                handler.post(r);
                devam.setEnabled(false);
                dur.setEnabled(true);
            }
        });

        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                time2.setText("kronometre:"+sayac++);
                handler.postDelayed(r,1000);
            }
        };
        handler.post(r);
       //yanlış kullanım
        /*do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time2.setText("kronometre:"+sayac++);

        }while(sayac<100);*/

    }
}
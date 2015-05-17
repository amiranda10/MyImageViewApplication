package com.proyect.robotino.myimageviewapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {
    ViewFlipper vf;
    Bitmap bitmap;
    ImageView iv;
    TextView tv;
    int contador;
    int imgIds[] = {R.drawable.enigma, R.drawable.fenice, R.drawable.robotino_distancesensor_layout, R.drawable.robotino_motor_position};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setImagen((ImageView) findViewById(R.id.imageView),BitmapFactory.decodeResource(getResources(), R.drawable.enigma));
        //vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        //vf.setFlipInterval(500);
        //vf.startFlipping();
        tv = (TextView) findViewById(R.id.textView);
        iv = (ImageView) findViewById(R.id.imageView);
        contador=0;
        Timer count = new Timer();
        bitmap = BitmapFactory.decodeResource(getResources(), imgIds[contador]);
        iv.setImageBitmap(bitmap);
        tv.setText(String.valueOf(contador));
        upContador();
        //count.schedule(new Tick(), 0, 1000);
    }

    public class Tick extends TimerTask
    {

        @Override
        public void run() {
            //cambioImagen(getCurrentFocus());
        }
    }

    public void upContador()
    {
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                cambioImagen();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    public void cambioImagen()
    {
        bitmap = BitmapFactory.decodeResource(getResources(), imgIds[contador]);
        iv.setImageBitmap(bitmap);
        //iv.setImageResource(R.drawable.enigma);
        //TextView textView = (TextView) findViewById(R.id.text_state);
        tv.setText(String.valueOf(contador));
        contador++;
        if(contador>3)
        {
            contador=0;
        }

    }

    public void cambioImagenButton(View view)
    {
        bitmap = BitmapFactory.decodeResource(getResources(), imgIds[contador]);
        iv.setImageBitmap(bitmap);
        //iv.setImageResource(R.drawable.enigma);
        //TextView textView = (TextView) findViewById(R.id.text_state);
        tv.setText(String.valueOf(contador));
        contador++;
        if(contador>3)
        {
            contador=0;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void setImageT(View view)
    {
        Thread nt = new Thread()
        {
            int imgIds[] = {R.drawable.enigma, R.drawable.fenice, R.drawable.robotino_distancesensor_layout, R.drawable.robotino_distancesensor_layout};
            int contador=0;
            @Override
            public void run()
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv = (ImageView) findViewById(R.id.imageView);
                        TextView tv = (TextView) findViewById(R.id.textView);
                        //bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.enigma);
                        bitmap= BitmapFactory.decodeResource(getResources(), imgIds[contador]);
                        tv.setText(String.valueOf(contador));
                        iv.setImageBitmap(bitmap);
                        contador++;
                        if(contador>=3)
                        {
                            contador=0;
                        }

                    }
                });
            }
        };

        nt.start();

    }
}

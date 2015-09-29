package com.shreyathegreat.kisanseva;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class SoilMapping extends Activity implements View.OnClickListener {
    Button b1, b2, b3, b4, b5, b6;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_mapping);
        b1 = (Button) findViewById(R.id.im1);
        b2 = (Button) findViewById(R.id.im2);
        b3 = (Button) findViewById(R.id.im3);
        b4 = (Button) findViewById(R.id.im4);
        b5 = (Button) findViewById(R.id.im5);
        b6 = (Button) findViewById(R.id.im6);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_soil_mapping, menu);
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

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.im1:
                position = "1";
                break;

            case R.id.im2:
                position = "2";
                Toast.makeText(SoilMapping.this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.im3:
                position = "3";
                Toast.makeText(SoilMapping.this, "3", Toast.LENGTH_SHORT).show();
                break;

            case R.id.im4:
                position = "4";
                Toast.makeText(SoilMapping.this, "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.im5:
                position = "5";
                Toast.makeText(SoilMapping.this, "5", Toast.LENGTH_SHORT).show();
                break;

            case R.id.im6:
                position = "6";
                Toast.makeText(SoilMapping.this, "6", Toast.LENGTH_SHORT).show();
                break;
            default:
                position = "0";
                break;
        }

        Intent intent = new Intent(SoilMapping.this, SoilMapping2.class);
        intent.putExtra("Pos", position);
        startActivity(intent);
    }
}

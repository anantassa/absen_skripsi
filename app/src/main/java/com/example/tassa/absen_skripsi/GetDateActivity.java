package com.example.tassa.absen_skripsi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Lenovo on 02/08/2017.
 */

public class GetDateActivity extends AppCompatActivity {
    View dialogView;
    TextView txtdate1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdate);

        // 1
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strdate1 = sdf1.format(c1.getTime());

        TextView txtdate1 = (TextView) findViewById(R.id.textView1);
        txtdate1.setText(strdate1);
        final String isi_topik = txtdate1.getText().toString().trim();

    }
}

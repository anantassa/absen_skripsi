package com.example.tassa.absen_skripsi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateAbsenActivity extends AppCompatActivity {

    private String id_jadwal, id_mahasiswa, noreg, nama, pertemuan_1, pertemuan_2;
    LihatAbsenModel lihatAbsenModel;
    EditText idj, idm,  p1, p2;
    TextView noreg_txt, nama_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_absen);

        id_jadwal = getIntent().getStringExtra(lihatAbsenModel.getId_jadwal());
        id_mahasiswa = getIntent().getStringExtra(lihatAbsenModel.getId_mahasiswa());
        noreg = getIntent().getStringExtra(lihatAbsenModel.getNoreg());
        nama = getIntent().getStringExtra(lihatAbsenModel.getNama());
        pertemuan_1 = getIntent().getStringExtra(lihatAbsenModel.getPertemuan_1());
        pertemuan_2 = getIntent().getStringExtra(lihatAbsenModel.getPertemuan_2());

        idj = (EditText) findViewById(R.id.editTextIDJ);
        idm = (EditText) findViewById(R.id.editTextIDM);
        noreg_txt = (TextView) findViewById(R.id.editTextNoreg);
        nama_txt = (TextView) findViewById(R.id.editTextNama);
        p1 = (EditText) findViewById(R.id.editTextP1);
        p2 = (EditText) findViewById(R.id.editTextP2);

        idj.setText(id_jadwal);
        idm.setText(id_mahasiswa);
        noreg_txt.setText(noreg);
        nama_txt.setText(nama);
        p1.setText(pertemuan_1);
        p2.setText(pertemuan_2);

    }


}

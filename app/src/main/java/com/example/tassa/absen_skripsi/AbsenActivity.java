package com.example.tassa.absen_skripsi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tassa.absen_skripsi.model.Mahasiswa;
import com.example.tassa.absen_skripsi.util.GetUserData;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class AbsenActivity extends AppCompatActivity {

    private Spinner spinner;
    Form05Model form05Model;
    JadwalModel jadwalModel;
    LihatAbsenModel lihatAbsenModel;
    JSONArray jsonArray;
    private String id_jadwal, id_mahasiswa, id_jadwal_et, semester, kode_seksi, nama_prodi, matkul, nama_dosen1, hari, gedung, ruang, kode_jam;
    Button absen;
    static String level;
    private TextView idj, sems, kodes, matkul_abs, dosen_abs, prodi_abs, hari_abs, ruang_abs, jam_abs, gedung_abs;
//    GetUserData cls2 = new GetUserData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses_absen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        id_jadwal = getIntent().getExtras().getString("id_jadwal");
     //   id_mahasiswa = getIntent().getExtras().getString("id_mahasiswa");
        Mahasiswa mahasiswa = (Mahasiswa) GetUserData.getData(getApplicationContext());
        id_mahasiswa = mahasiswa.id_mahasiswa;

        id_jadwal_et = getIntent().getStringExtra(jadwalModel.getId_jadwal());
        semester = getIntent().getStringExtra(jadwalModel.getSemester());
        nama_prodi = getIntent().getStringExtra(jadwalModel.getNama_prodi());
        kode_seksi = getIntent().getStringExtra(jadwalModel.getKode_seksi());
        matkul = getIntent().getStringExtra(jadwalModel.getMatkul());
        nama_dosen1 = getIntent().getStringExtra(jadwalModel.getNama_dosen1());
        hari = getIntent().getStringExtra(jadwalModel.getHari());
        kode_jam = getIntent().getStringExtra(jadwalModel.getKode_jam());
        gedung = getIntent().getStringExtra(jadwalModel.getGedung());
        ruang = getIntent().getStringExtra(jadwalModel.getRuang());


        sems = (TextView) findViewById(R.id.absen_sems);
        prodi_abs = (TextView) findViewById(R.id.absen_prodi);
        kodes = (TextView) findViewById(R.id.absen_kode_seksi);
        matkul_abs = (TextView) findViewById(R.id.absen_matkul);
        dosen_abs = (TextView) findViewById(R.id.absen_dosen);

        sems.setText(semester);
        prodi_abs.setText(nama_prodi);
        kodes.setText(kode_seksi);
        matkul_abs.setText(matkul);
        dosen_abs.setText(nama_dosen1);

        spinner = (Spinner) findViewById(R.id.spinner_absen);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.absen_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        absen = (Button) findViewById(R.id.btn_absen);
        absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAbsen(id_jadwal);
            }
        });
    }

    private void postAbsen(final String id_jadwal) {
        String tag_string_req = "req_load_form";
//
        final String item = spinner.getSelectedItem().toString().trim();
        String url_select = Server.URL + "update_absen.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_select,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(AbsenActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AbsenActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_jadwal", id_jadwal);
                params.put("id_mahasiswa", id_mahasiswa);
                params.put("item", item);
             //   params.put("isi_topik", isi_topik);
                //  params.put(USEREMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}

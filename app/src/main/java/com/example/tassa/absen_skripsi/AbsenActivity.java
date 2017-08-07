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
    private String id_jadwal, id_mahasiswa;
    Button absen;
    static String level;
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

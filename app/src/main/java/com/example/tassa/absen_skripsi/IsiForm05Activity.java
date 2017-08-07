package com.example.tassa.absen_skripsi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.tassa.absen_skripsi.R.id.spinner;

public class IsiForm05Activity extends AppCompatActivity  {
    ArrayList<HashMap<String, String>> DaftarForm = new ArrayList<>();
    Toolbar toolbar;
    ListView listform;
    // List<Form05Model> itemList = new ArrayList<JadwalModel>();
    Form05Adapter adapter;
    int success;
    LayoutInflater inflater;

    // String id_jadwal, semester, nama_prodi, kode_seksi, matkul, nama_dosen1, hari, kode_jam, ruang, gedung;

    Form05Model form05Model;
    JadwalModel jadwalModel;
    JSONArray jsonArray;
    private String id_jadwal, item;
    private Button btn_submit;
    private EditText topik;
    private Spinner spinner;
//   public String item;
    String isi_topik;
    private static final String TAG = IsiForm05Activity.class.getSimpleName();
   // String x;

    String tag_json_obj = "json_obj_req";
    public static final String ID_JADWAL = "id_jadwal";

    public static final String FORM_1 = "form_1";
    public static final String FORM_2 = "form_2";
    public static final String FORM_3 = "form_3";
    public static final String FORM_4 = "form_4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_form05);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        id_jadwal = getIntent().getExtras().getString("id_jadwal");
        topik = (EditText) findViewById(R.id.txt_form);

        spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.form05_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postForm(id_jadwal);
            }
        });
    }



    private void postForm(final String id_jadwal) {
        String tag_string_req = "req_load_form";
//        pDialog.setMessage("Memuat ...");
        //  Log.v("EditText", topik.getText().toString());
         final String isi_topik = topik.getText().toString().trim();
     //   final String form = item.trim();
        //  final String isi_topik = topik.getText().toString().trim();
        final String item = spinner.getSelectedItem().toString().trim();
        String url_select = Server.URL + "update_form05.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_select,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(IsiForm05Activity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(IsiForm05Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_jadwal", id_jadwal);
                params.put("item", item);
                params.put("isi_topik", isi_topik);
                //  params.put(USEREMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}

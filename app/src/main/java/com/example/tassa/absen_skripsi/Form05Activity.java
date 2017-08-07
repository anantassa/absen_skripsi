package com.example.tassa.absen_skripsi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 01/08/2017.
 */

public class Form05Activity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> DaftarForm = new ArrayList<>();
    Toolbar toolbar;
    ListView list;
   // List<Form05Model> itemList = new ArrayList<Form05Model>();
    Form05Adapter adapter;
    int success;
    LayoutInflater inflater;
    JadwalModel jadwalModel;
    // String id_jadwal, semester, nama_prodi, kode_seksi, matkul, nama_dosen1, hari, kode_jam, ruang, gedung;

    Form05Model form05Model;
   // JadwalModel jadwalModel;
    JSONArray jsonArray;
    private String id_jadwal, semester, nama_prodi, kode_seksi, matkul, nama_dosen1, hari, kode_jam, ruang, gedung;


    private static final String TAG = Form05Activity.class.getSimpleName();

  //  private static String url_select = Server.URL + "absen_android/get_jadwal";


    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";



    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form05);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        id_jadwal = getIntent().getStringExtra(jadwalModel.getId_jadwal());
        semester = getIntent().getStringExtra(jadwalModel.getSemester());
        nama_prodi = getIntent().getStringExtra(jadwalModel.getNama_prodi());
        kode_seksi = getIntent().getStringExtra(jadwalModel.getKode_seksi());
        matkul = getIntent().getStringExtra(jadwalModel.getMatkul());
        nama_dosen1 = getIntent().getStringExtra(jadwalModel.getNama_dosen1());
        hari = getIntent().getStringExtra(jadwalModel.getHari());
        kode_jam = getIntent().getStringExtra(jadwalModel.getKode_jam());
        gedung = getIntent().getStringExtra(jadwalModel.getGedung());
        ruang = getIntent().getStringExtra(jadwalModel.getRuang());
        // menghubungkan variablel pada layout dan pada java
        list = (ListView) findViewById(R.id.list_form);

        DaftarForm  = new ArrayList<>();

        //   callVolley();


        loadForm05(id_jadwal);

    }


    private void loadForm05(final String id_resep){
        String tag_string_req = "req_load_form";
//        pDialog.setMessage("Memuat ...");


        StringRequest strReq = new StringRequest(Request.Method.POST, Form05Model.GET_FORM, new Response.Listener
                <String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Form Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    jsonArray = jObj.getJSONArray("form05");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject c = jsonArray.getJSONObject(i);
                        String id_jadwal = c.getString("id_jadwal");
                        String id_form = c.getString("id_form");
                        String form_1 = c.getString("form_1");
                        String form_2 = c.getString("form_2");
                        String form_3 = c.getString("form_3");
                        String form_4 = c.getString("form_4");

                        HashMap<String,String> map_form = new HashMap<>()
                                ;
                        map_form.put(form05Model.id_jadwal,id_jadwal);
                        map_form.put(form05Model.id_form,id_form);
                        map_form.put(form05Model.form_1,form_1);
                        map_form.put(form05Model.form_2,form_2);
                        map_form.put(form05Model.form_3,form_3);
                        map_form.put(form05Model.form_4,form_4);

                        DaftarForm.add(map_form);
//                        if(db.isExists(daftar_resep.get(resepModel.getId_resep()))){
//                            favouriteIcon.setImageResource(R.drawable.heart_black);
//                        } else {
//                            favouriteIcon.setImageResource(R.drawable.heart_white);
//                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SetListForm(DaftarForm);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Jadwal Load Error: " + error.getMessage());
                Toast.makeText(Form05Activity.this,
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_jadwal", id_jadwal);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void SetListForm(ArrayList<HashMap<String, String>> daftarForm) {
        if(daftarForm.size() == 0) {
            adapter = new Form05Adapter(Form05Activity.this, new ArrayList<HashMap<String, String>>());
//            emptyTV.setText("Tidak ada komentar");
            list.setAdapter(adapter);
            adapter.notifyDataSetInvalidated();
        } else {
            adapter = new Form05Adapter(Form05Activity.this, daftarForm);
            list.setAdapter(adapter);
//            UIUtils.setListViewHeightBasedOnItems(komentar);
            adapter.notifyDataSetChanged();
        }

    }

}




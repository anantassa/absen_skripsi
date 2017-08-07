package com.example.tassa.absen_skripsi;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Form05Fragment extends Fragment {
    private String id_jadwal;

        private ListView listform;
        ArrayList<HashMap<String, String>> DaftarForm = new ArrayList<>();
      //  ProgressDialog pDialog;
        private static final String TAG = DetailActivity.class.getSimpleName();
        Form05Model form05Model;
         JadwalModel jadwalModel;
         Form05Adapter adapter;
        JSONArray jsonArray;
        FloatingActionButton fab;

        public Form05Fragment() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rowView = inflater.inflate(R.layout.fragment_form05, container, false);

            id_jadwal = getActivity().getIntent().getStringExtra(jadwalModel.getId_jadwal());

            listform = (ListView)rowView.findViewById(R.id.list_form05);
            fab     = (FloatingActionButton)rowView.findViewById(R.id.fab_add);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity().getApplicationContext(),IsiForm05Activity.class);
                 //   HashMap<String,String> map = DaftarForm.get(position);
                    i.putExtra("id_jadwal", id_jadwal);
                    startActivity(i);
                }
            });

            loadForm(id_jadwal);

            return rowView;
        }



        private void loadForm(final String id_jadwal){
            String tag_string_req = "req_load_form";


            StringRequest strReq = new StringRequest(Request.Method.POST, Form05Model.GET_FORM, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Form Response: " + response.toString());
                  //  hideDialog();

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
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    SetListForm(DaftarForm);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Form Load Error: " + error.getMessage());
                    Toast.makeText(getActivity(),
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
                adapter = new Form05Adapter(getActivity(), new ArrayList<HashMap<String, String>>());
                listform.setAdapter(adapter);
                adapter.notifyDataSetInvalidated();
            } else {
                adapter = new Form05Adapter(getActivity(), daftarForm);
                listform.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }



}
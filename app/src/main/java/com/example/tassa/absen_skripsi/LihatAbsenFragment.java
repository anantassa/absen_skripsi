package com.example.tassa.absen_skripsi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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
public class LihatAbsenFragment extends Fragment {

    private String id_jadwal;

    private ListView listabsen, listcount;
    ArrayList<HashMap<String, String>> DaftarCount = new ArrayList<>();
    ArrayList<HashMap<String, String>> DaftarAbsen = new ArrayList<>();
    //  ProgressDialog pDialog;
    private static final String TAG = DetailActivity.class.getSimpleName();
    LihatAbsenModel lihatAbsenModel;
    JadwalModel jadwalModel;
    LihatAbsenAdapter adapter;
    CountModel countModel;
    CountAdapter countAdapter;
    JSONArray jsonArray;

    public LihatAbsenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rowView = inflater.inflate(R.layout.fragment_lihat_absen, container, false);

        id_jadwal = getActivity().getIntent().getStringExtra(jadwalModel.getId_jadwal());

        listabsen = (ListView) rowView.findViewById(R.id.list_lihat_absen);
        listcount = (ListView) rowView.findViewById(R.id.list_count);

        loadAbsen(id_jadwal);
        loadCount(id_jadwal);

        listabsen.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> map = DaftarAbsen.get(position);
                Intent i = new Intent(getActivity().getApplicationContext(),UpdateAbsenActivity.class);
                i.putExtra(lihatAbsenModel.id_jadwal,map.get(lihatAbsenModel.id_jadwal));
                i.putExtra(lihatAbsenModel.id_mahasiswa,map.get(lihatAbsenModel.id_mahasiswa));
                i.putExtra(lihatAbsenModel.noreg,map.get(lihatAbsenModel.noreg));
                i.putExtra(lihatAbsenModel.nama,map.get(lihatAbsenModel.nama));
                i.putExtra(lihatAbsenModel.pertemuan_1,map.get(lihatAbsenModel.pertemuan_1));
                i.putExtra(lihatAbsenModel.pertemuan_2,map.get(lihatAbsenModel.pertemuan_2));

                startActivity(i);
            }

        });



        return rowView;
    }


    private void loadAbsen(final String id_jadwal) {
        String tag_string_req = "req_load_absen";


        StringRequest strReq = new StringRequest(Request.Method.POST, LihatAbsenModel.GET_ABSEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Absen Response: " + response.toString());
                //  hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    jsonArray = jObj.getJSONArray("jadwal_mhs");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);
                        String id_jadwal = c.getString("id_jadwal");
                        String id_mahasiswa = c.getString("id_mahasiswa");
                        String noreg = c.getString("noreg");
                        String nama = c.getString("nama");
                        String pertemuan_1 = c.getString("pertemuan_1");
                        String pertemuan_2 = c.getString("pertemuan_2");

                        HashMap<String, String> map_absen = new HashMap<>();
                        map_absen.put(lihatAbsenModel.id_jadwal, id_jadwal);
                        map_absen.put(lihatAbsenModel.id_mahasiswa, id_mahasiswa);
                        map_absen.put(lihatAbsenModel.noreg, noreg);
                        map_absen.put(lihatAbsenModel.nama, nama);
                        map_absen.put(lihatAbsenModel.pertemuan_1, pertemuan_1);
                        map_absen.put(lihatAbsenModel.pertemuan_2, pertemuan_2);


                        DaftarAbsen.add(map_absen);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SetListAbsen(DaftarAbsen);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Absen Load Error: " + error.getMessage());
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

    private void SetListAbsen(ArrayList<HashMap<String, String>> daftarAbsen) {
        if (daftarAbsen.size() == 0) {
            adapter = new LihatAbsenAdapter(getActivity(), new ArrayList<HashMap<String, String>>());
            listabsen.setAdapter(adapter);
            adapter.notifyDataSetInvalidated();
        } else {
            adapter = new LihatAbsenAdapter(getActivity(), daftarAbsen);
            listabsen.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private void loadCount(final String id_jadwal) {
        String tag_string_req = "req_load_count";


        StringRequest strReq = new StringRequest(Request.Method.POST, CountModel.GET_COUNT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Count Response: " + response.toString());
                //  hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    jsonArray = jObj.getJSONArray("count");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);
                        String cp1 = c.getString("cp1");
                        String cp2 = c.getString("cp2");

                        HashMap<String, String> map_count = new HashMap<>();
                        map_count.put(countModel.cp1, cp1);
                        map_count.put(countModel.cp2, cp2);

                        DaftarCount.add(map_count);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SetListCount(DaftarCount);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Count Load Error: " + error.getMessage());
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

    private void SetListCount(ArrayList<HashMap<String, String>> daftarCount) {
        if (daftarCount.size() == 0) {
            countAdapter = new CountAdapter(getActivity(), new ArrayList<HashMap<String, String>>());
            listcount.setAdapter(countAdapter);
            countAdapter.notifyDataSetInvalidated();
        } else {
            countAdapter = new CountAdapter(getActivity(), daftarCount);
            listcount.setAdapter(countAdapter);
            countAdapter.notifyDataSetChanged();
        }
    }
}




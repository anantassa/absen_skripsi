package com.example.tassa.absen_skripsi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private ListView listabsen;
    ArrayList<HashMap<String, String>> DaftarAbsen = new ArrayList<>();
    //  ProgressDialog pDialog;
    private static final String TAG = DetailActivity.class.getSimpleName();
    LihatAbsenModel lihatAbsenModel;
    JadwalModel jadwalModel;
    LihatAbsenAdapter adapter;
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

        loadAbsen(id_jadwal);

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
}




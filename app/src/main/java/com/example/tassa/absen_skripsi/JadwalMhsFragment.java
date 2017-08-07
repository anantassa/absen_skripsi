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
public class JadwalMhsFragment extends Fragment {
    ListView list;
    ArrayList<HashMap<String, String>> DaftarJadwal = new ArrayList<>();
    JadwalModel jadwalModel;
    JSONArray jsonArray;
    MatkulAdapter adapter;

    private static final String TAG = MatkulActivity.class.getSimpleName();

    public JadwalMhsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_jadwal_mhs, container, false);
        list = (ListView) rootView.findViewById(R.id.list_absen);

        DaftarJadwal = new ArrayList<>();
        loadJadwal();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = DaftarJadwal.get(position);
                Intent i = new Intent(getActivity().getApplicationContext(), AbsenActivity.class);
                i.putExtra(jadwalModel.id_jadwal, map.get(jadwalModel.id_jadwal));
                i.putExtra(jadwalModel.semester, map.get(jadwalModel.semester));
                i.putExtra(jadwalModel.kode_seksi, map.get(jadwalModel.kode_seksi));
                i.putExtra(jadwalModel.matkul, map.get(jadwalModel.matkul));
                i.putExtra(jadwalModel.nama_prodi, map.get(jadwalModel.nama_prodi));
                i.putExtra(jadwalModel.nama_dosen1, map.get(jadwalModel.nama_dosen1));
                i.putExtra(jadwalModel.hari, map.get(jadwalModel.hari));
                i.putExtra(jadwalModel.kode_jam, map.get(jadwalModel.kode_jam));
                i.putExtra(jadwalModel.gedung, map.get(jadwalModel.gedung));
                i.putExtra(jadwalModel.ruang, map.get(jadwalModel.ruang));
                startActivity(i);
            }

        });



        return rootView;
    }




    private void loadJadwal(){
        String tag_string_req = "req_load_jadwal";
//        pDialog.setMessage("Memuat ...");


        StringRequest strReq = new StringRequest(Request.Method.POST, JadwalModel.GET_JADWAL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Jadwal Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    jsonArray = jObj.getJSONArray("input_jadwal");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject c = jsonArray.getJSONObject(i);
                        String id_jadwal = c.getString("id_jadwal");
                        String semester = c.getString("semester");
                        String nama_prodi = c.getString("nama_prodi");
                        String kode_seksi = c.getString("kode_seksi");
                        String matkul = c.getString("matkul");
                        String nama_dosen1 = c.getString("nama_dosen1");
                        String hari = c.getString("hari");
                        String kode_jam = c.getString("kode_jam");
                        String gedung = c.getString("gedung");
                        String ruang = c.getString("ruang");
                        HashMap<String,String> map_jadwal = new HashMap<>()
                                ;
                        map_jadwal.put(jadwalModel.id_jadwal,id_jadwal);
                        map_jadwal.put(jadwalModel.semester,semester);
                        map_jadwal.put(jadwalModel.nama_prodi,nama_prodi);
                        map_jadwal.put(jadwalModel.kode_seksi,kode_seksi);
                        map_jadwal.put(jadwalModel.matkul,matkul);
                        map_jadwal.put(jadwalModel.nama_dosen1,nama_dosen1);
                        map_jadwal.put(jadwalModel.hari,hari);
                        map_jadwal.put(jadwalModel.kode_jam,kode_jam);
                        map_jadwal.put(jadwalModel.gedung,gedung);
                        map_jadwal.put(jadwalModel.ruang,ruang);
                        DaftarJadwal.add(map_jadwal);
//                        if(db.isExists(daftar_resep.get(resepModel.getId_resep()))){
//                            favouriteIcon.setImageResource(R.drawable.heart_black);
//                        } else {
//                            favouriteIcon.setImageResource(R.drawable.heart_white);
//                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SetListJadwal(DaftarJadwal);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Jadwal Load Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
//                params.put("id_kota", id_kota);
//                params.put("id_wisata", id_wisata);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void SetListJadwal(ArrayList<HashMap<String, String>> daftarJadwal) {
        if(daftarJadwal.size() == 0) {
            adapter = new MatkulAdapter(getActivity(), new ArrayList<HashMap<String, String>>());
//            emptyTV.setText("Tidak ada komentar");
            list.setAdapter(adapter);
            adapter.notifyDataSetInvalidated();
        } else {
            adapter = new MatkulAdapter(getActivity(), daftarJadwal);
            list.setAdapter(adapter);
//            UIUtils.setListViewHeightBasedOnItems(komentar);
            adapter.notifyDataSetChanged();
        }

    }

}



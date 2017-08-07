package com.example.tassa.absen_skripsi;

/**
 * Created by Lenovo on 29/07/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kuncoro on 26/03/2016.
 */
public class MatkulAdapter extends BaseAdapter {

    private Activity activity;
    private MatkulAdapter matkulAdapter;
    private JadwalModel jadwalModel;
    //  private MenuUtama menuUtama;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    //  private WishListModel db;

    public MatkulAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //  db = new WishListModel(ListAdapter.inflater.getContext());

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if (convertView == null)
            rowView = inflater.inflate(R.layout.list_absen, null);

        TextView id_jadwal = (TextView) rowView.findViewById(R.id.id_jadwal);
        TextView semester = (TextView) rowView.findViewById(R.id.semester);
        TextView nama_prodi = (TextView) rowView.findViewById(R.id.nama_prodi);
        TextView kode_seksi = (TextView) rowView.findViewById(R.id.kode_seksi);
        TextView matkul = (TextView) rowView.findViewById(R.id.matkul);
        TextView nama_dosen1 = (TextView) rowView.findViewById(R.id.nama_dosen1);
        TextView hari = (TextView) rowView.findViewById(R.id.hari);
        TextView kode_jam = (TextView) rowView.findViewById(R.id.hari);
        TextView gedung = (TextView) rowView.findViewById(R.id.gedung);
        TextView ruang = (TextView) rowView.findViewById(R.id.ruang);

        HashMap<String, String> daftar_jadwal = new HashMap<String, String>();
        daftar_jadwal = data.get(position);

        id_jadwal.setText(daftar_jadwal.get(jadwalModel.getId_jadwal()));
        semester.setText(daftar_jadwal.get(jadwalModel.getSemester()));
        nama_prodi.setText(daftar_jadwal.get(jadwalModel.getNama_prodi()));
        kode_seksi.setText(daftar_jadwal.get(jadwalModel.getKode_seksi()));
        matkul.setText(daftar_jadwal.get(jadwalModel.getMatkul()));
        nama_dosen1.setText(daftar_jadwal.get(jadwalModel.getNama_dosen1()));
        hari.setText(daftar_jadwal.get(jadwalModel.getHari()));
        kode_jam.setText(daftar_jadwal.get(jadwalModel.getKode_jam()));
        gedung.setText(daftar_jadwal.get(jadwalModel.getGedung()));
        ruang.setText(daftar_jadwal.get(jadwalModel.getRuang()));




    //  final HashMap<String, String> finalDaftar_jadwal = daftar_jadwal;


        return rowView;
    }


}
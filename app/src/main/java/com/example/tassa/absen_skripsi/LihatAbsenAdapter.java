package com.example.tassa.absen_skripsi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lenovo on 02/08/2017.
 */

public class LihatAbsenAdapter extends BaseAdapter {
    private Activity activity;
    private LihatAbsenAdapter lihatAbsenAdapter;
    private LihatAbsenModel lihatAbsenModel;
    //  private MenuUtama menuUtama;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    //  private WishListModel db;

    public LihatAbsenAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
            rowView = inflater.inflate(R.layout.list_lihat_absen, null);

        TextView id_jadwal = (TextView) rowView.findViewById(R.id.id_jadwal);
        TextView id_mahasiswa = (TextView) rowView.findViewById(R.id.id_mahasiswa);
        TextView noreg = (TextView) rowView.findViewById(R.id.noreg);
        TextView nama = (TextView) rowView.findViewById(R.id.nama);
        TextView pertemuan_1 = (TextView) rowView.findViewById(R.id.pertemuan_1);
        TextView pertemuan_2 = (TextView) rowView.findViewById(R.id.pertemuan_2);


        HashMap<String, String> daftar_absen = new HashMap<String, String>();
        daftar_absen = data.get(position);

        id_jadwal.setText(daftar_absen.get(lihatAbsenModel.getId_jadwal()));
        id_mahasiswa.setText(daftar_absen.get(lihatAbsenModel.getId_mahasiswa()));
        noreg.setText(daftar_absen.get(lihatAbsenModel.getNoreg()));
        nama.setText(daftar_absen.get(lihatAbsenModel.getNama()));
        pertemuan_1.setText(daftar_absen.get(lihatAbsenModel.getPertemuan_1()));
        pertemuan_2.setText(daftar_absen.get(lihatAbsenModel.getPertemuan_2()));





        //  final HashMap<String, String> finalDaftar_jadwal = daftar_jadwal;


        return rowView;
    }


}

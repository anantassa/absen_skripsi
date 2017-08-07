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
 * Created by Lenovo on 01/08/2017.
 */

public class Form05Adapter extends BaseAdapter {

    private Activity activity;
    private Form05Adapter form05Adapter;
    private Form05Model form05Model;
    //  private MenuUtama menuUtama;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    //  private WishListModel db;

    public Form05Adapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
            rowView = inflater.inflate(R.layout.list_form, null);

        TextView id_jadwal = (TextView) rowView.findViewById(R.id.id_jadwal);
        TextView id_form = (TextView) rowView.findViewById(R.id.id_form);
        TextView form_1 = (TextView) rowView.findViewById(R.id.form_1);
        TextView form_2 = (TextView) rowView.findViewById(R.id.form_2);
        TextView form_3 = (TextView) rowView.findViewById(R.id.form_3);
        TextView form_4 = (TextView) rowView.findViewById(R.id.form_4);


        HashMap<String, String> daftar_form = new HashMap<String, String>();
        daftar_form = data.get(position);

        id_jadwal.setText(daftar_form.get(form05Model.getId_jadwal()));
        id_form.setText(daftar_form.get(form05Model.getId_form()));
        form_1.setText(daftar_form.get(form05Model.getForm_1()));
        form_2.setText(daftar_form.get(form05Model.getForm_2()));
        form_3.setText(daftar_form.get(form05Model.getForm_3()));
        form_4.setText(daftar_form.get(form05Model.getForm_4()));




        //  final HashMap<String, String> finalDaftar_jadwal = daftar_jadwal;


        return rowView;
    }


}

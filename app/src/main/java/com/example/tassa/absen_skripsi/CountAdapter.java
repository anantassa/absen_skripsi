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
 * Created by Lenovo on 09/08/2017.
 */

public class CountAdapter extends BaseAdapter{

    private Activity activity;
  //  private MatkulAdapter matkulAdapter;
    private CountModel countModel;
    //  private MenuUtama menuUtama;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    //  private WishListModel db;

    public CountAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
            rowView = inflater.inflate(R.layout.list_count, null);

        TextView cp1 = (TextView) rowView.findViewById(R.id.cp1);
        TextView cp2 = (TextView) rowView.findViewById(R.id.cp2);


        HashMap<String, String> daftar_count = new HashMap<String, String>();
        daftar_count = data.get(position);

        cp1.setText(daftar_count.get(countModel.getCp1()));
        cp2.setText(daftar_count.get(countModel.getCp2()));




        //  final HashMap<String, String> finalDaftar_jadwal = daftar_jadwal;


        return rowView;
    }


}


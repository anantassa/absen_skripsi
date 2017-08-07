package com.example.tassa.absen_skripsi.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tassa.absen_skripsi.Config;
import com.example.tassa.absen_skripsi.model.Mahasiswa;
import com.google.gson.Gson;

/**
 * Created by Lenovo on 06/08/2017.
 */

public class GetUserData {
    public static Object getData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String session_pref = sharedPreferences.getString(Config.SESSION_PREF, "");
        String level = sharedPreferences.getString(Config.USER_LEVEL_PREF, "");
        if(level.trim().equalsIgnoreCase("mahasiswa") && session_pref != ""){
            Mahasiswa mahasiswa = new Gson().fromJson(session_pref, Mahasiswa.class);
            return mahasiswa;
        }else {
            if(level.trim().equalsIgnoreCase("dosen") && session_pref != ""){
                //Mahasiswa d = new Gson().fromJson(session_pref, Mahasiswa.class);
                return null;
            }
        }
        return  null;
    }

}

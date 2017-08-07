package com.example.tassa.absen_skripsi;

/**
 * Created by Lenovo on 02/08/2017.
 */

public class LihatAbsenModel {
    public static final String BASE_URL="https://anantassafitri.000webhostapp.com/";
    public static final String GET_ABSEN=BASE_URL+"absen_android/get_mahasiswa";

    public static final String id_jadwal = "id_jadwal";
    public static final String id_mahasiswa = "id_mahasiswa";
    public static final String noreg = "noreg";
    public static final String nama = "nama";
    public static final String pertemuan_1 = "pertemuan_1";
    public static final String pertemuan_2 = "pertemuan_2";

    public static String getId_jadwal() {
        return id_jadwal;
    }

    public static String getId_mahasiswa() {
        return id_mahasiswa;
    }

    public static String getNoreg() {
        return noreg;
    }

    public static String getNama() {
        return nama;
    }

    public static String getPertemuan_1() {
        return pertemuan_1;
    }

    public static String getPertemuan_2() {
        return pertemuan_2;
    }



}

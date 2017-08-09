package com.example.tassa.absen_skripsi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lenovo on 01/08/2017.
 */

public class DetailActivity extends AppCompatActivity {


    private static final int RECOVERY_REQUEST = 1;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TextView matkul_txt, dosen_txt, prodi_txt;
    private String id_jadwal, semester, nama_prodi, kode_seksi, matkul, nama_dosen1, hari, kode_jam, ruang, gedung;
    JadwalModel jadwalModel;
    AppCompatActivity app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        matkul_txt = (TextView) findViewById(R.id.matkul_form);
        dosen_txt = (TextView) findViewById(R.id.dosen_form);
        prodi_txt = (TextView) findViewById(R.id.prodi_form);


//        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
//        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        //   db = new WishListModel(getApplicationContext());

        id_jadwal = getIntent().getStringExtra(jadwalModel.getId_jadwal());
        semester = getIntent().getStringExtra(jadwalModel.getSemester());
        nama_prodi = getIntent().getStringExtra(jadwalModel.getNama_prodi());
        kode_seksi = getIntent().getStringExtra(jadwalModel.getKode_seksi());
        matkul = getIntent().getStringExtra(jadwalModel.getMatkul());
        nama_dosen1 = getIntent().getStringExtra(jadwalModel.getNama_dosen1());
        hari = getIntent().getStringExtra(jadwalModel.getHari());
        kode_jam = getIntent().getStringExtra(jadwalModel.getKode_jam());
        gedung = getIntent().getStringExtra(jadwalModel.getGedung());
        ruang = getIntent().getStringExtra(jadwalModel.getRuang());

        matkul_txt.setText(matkul);
        dosen_txt.setText(nama_dosen1);
        prodi_txt.setText(nama_prodi);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private static final int FRAGMENT_COUNT = 2;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return new Form05Fragment();
                case 1:
                    return new LihatAbsenFragment();
            }
            return null;
//            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return FRAGMENT_COUNT;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Form 05";
                case 1:
                    return "Lihat Absen";
            }
            return null;
        }
    }


}

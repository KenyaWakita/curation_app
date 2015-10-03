//package com.example.kenyawakita.sssss;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.util.Log;
//
//public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter
//{
//    public MyFragmentStatePagerAdapter(FragmentManager fm) {
//        super(fm);
//    }
//    @Override public Fragment getItem(int i) {
//        Log.d("log", String.valueOf(i));
//        switch(i){
//            case 0:
//                return new TicketActivity();
//            case 1:
//                return new BlogActivity();
//            default:
//                return new NewsActivity();
//        }
//    }
//    @Override public int getCount() {
//        return 3;
//    }
//    @Override public CharSequence getPageTitle(int position) {
//        return "Page " + position;
//    }
//}

package kenyawakita.sssss;

import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // タイトルを非表示にする
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });


        ActionBar.Tab tab =actionBar.newTab();
        tab.setCustomView(R.layout.tab1);
        tab.setTabListener(this);
        actionBar.addTab(tab);
        TextView textView = (TextView) findViewById(R.id.tab1_title);
        textView.setText("ブログ");
        ImageView image = (ImageView) findViewById(R.id.tab1_icon);
        image.setImageResource(R.drawable.blog1);

        ActionBar.Tab tab2 =actionBar.newTab();
        tab2.setCustomView(R.layout.tab2);
        tab2.setTabListener(this);
        actionBar.addTab(tab2);
        TextView textView2 = (TextView) findViewById(R.id.tab2_title);
        textView2.setText("チケット");
        ImageView image2 = (ImageView) findViewById(R.id.tab2_icon);
        image2.setImageResource(R.drawable.tag79);


        ActionBar.Tab tab3 =actionBar.newTab();
        tab3.setCustomView(R.layout.tab3);
        tab3.setTabListener(this);
        actionBar.addTab(tab3);
        TextView textView3 = (TextView) findViewById(R.id.tab3_title);
        textView3.setText("ニュース");
        ImageView image3 = (ImageView) findViewById(R.id.tab3_icon);
        image3.setImageResource(R.drawable.newspaper9);


        ActionBar.Tab tab4 =actionBar.newTab();
        tab4.setCustomView(R.layout.tab4);
        tab4.setTabListener(this);
        actionBar.addTab(tab4);
        TextView textView4 = (TextView) findViewById(R.id.tab4_title);
        textView4.setText("YOUTUBE");
        ImageView image4 = (ImageView) findViewById(R.id.tab4_icon);
        image4.setImageResource(R.drawable.video107);


        ActionBar.Tab tab5 =actionBar.newTab();
        tab5.setCustomView(R.layout.tab5);
        tab5.setTabListener(this);
        actionBar.addTab(tab5);
        TextView textView5 = (TextView) findViewById(R.id.tab5_title);
        textView5.setText("その他");
        ImageView image5 = (ImageView) findViewById(R.id.tab5_icon);
        image5.setImageResource(R.drawable.ortographic1);



        // For each of the sections in the app, add a tab to the action bar.
//        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
//            // Create a tab with text corresponding to the page title defined by
//            // the adapter. Also specify this Activity object, which implements
//            // the TabListener interface, as the callback (listener) for when
//            // this tab is selected.
//            actionBar.addTab(
//                    actionBar.newTab()
//                            .setText(mSectionsPagerAdapter.getPageTitle(i))
//                            .setTabListener(this));
//        }


    }



    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new BlogActivity();
                case 1:
                    return new TicketActivity();
                case 2:
                    return new NewsActivity();
                case 3:
                    return new YoutubeActivity();
                case 4:
                    return new OtherActivity();
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
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

        public PlaceholderFragment() {
        }

    }

}

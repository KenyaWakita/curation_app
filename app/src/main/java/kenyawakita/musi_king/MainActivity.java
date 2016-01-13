package kenyawakita.musi_king;

import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // タイトルを非表示にする
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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
        textView.setText("Androidランキング");
        ImageView image = (ImageView) findViewById(R.id.tab1_icon);
        image.setImageResource(R.drawable.android);

        ActionBar.Tab tab2 =actionBar.newTab();
        tab2.setCustomView(R.layout.tab2);
        tab2.setTabListener(this);
        actionBar.addTab(tab2);
        TextView textView2 = (TextView) findViewById(R.id.tab2_title);
        textView2.setText("iphoneランキング");
        ImageView image2 = (ImageView) findViewById(R.id.tab2_icon);
        image2.setImageResource(R.drawable.apple);


        ActionBar.Tab tab3 =actionBar.newTab();
        tab3.setCustomView(R.layout.tab3);
        tab3.setTabListener(this);
        actionBar.addTab(tab3);
        TextView textView3 = (TextView) findViewById(R.id.tab3_title);
        textView3.setText("Androidニュース");
        ImageView image3 = (ImageView) findViewById(R.id.tab3_icon);
        image3.setImageResource(R.drawable.android);


        ActionBar.Tab tab4 =actionBar.newTab();
        tab4.setCustomView(R.layout.tab4);
        tab4.setTabListener(this);
        actionBar.addTab(tab4);
        TextView textView4 = (TextView) findViewById(R.id.tab4_title);
        textView4.setText("iphoneニュース");
        ImageView image4 = (ImageView) findViewById(R.id.tab4_icon);
        image4.setImageResource(R.drawable.apple);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
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
                    WebviewFragment app_rank_android = new WebviewFragment();
                    Bundle bundle_and = new Bundle();
                    bundle_and.putString("Tab_Name","Androidランキング");
                    bundle_and.putString("url", Constants.APP_RANK_ANDROID_URL);
                    app_rank_android.setArguments(bundle_and);
                    return app_rank_android;
                case 1:
                    WebviewFragment app_rank_iphone = new WebviewFragment();
                    Bundle bundle_ipho = new Bundle();
                    bundle_ipho.putString("Tab_Name","iphoneランキング");
                    bundle_ipho.putString("url", Constants.APP_RANK_IPHONE_URL);
                    app_rank_iphone.setArguments(bundle_ipho);
                    return app_rank_iphone;
                case 2:
                    BoardTypeFragment app_rank_android_article = new BoardTypeFragment();
                    Bundle bundle_andart = new Bundle();
                    bundle_andart.putStringArrayList("title",Constants.android_BoardType_title);
                    bundle_andart.putStringArrayList("href",Constants.android_BoardType_href);
                    bundle_andart.putString("json_url", Constants.APP_RANK_ANDROID_ARTICLE_JSON);
                    app_rank_android_article.setArguments(bundle_andart);
                    return app_rank_android_article;
                case 3:
                    BoardTypeFragment app_rank_iphone_article = new BoardTypeFragment();
                    Bundle bundle_iphoart = new Bundle();
                    bundle_iphoart.putStringArrayList("title",Constants.iphone_BoardType_title);
                    bundle_iphoart.putStringArrayList("href",Constants.iphone_BoardType_href);
                    bundle_iphoart.putString("json_url", Constants.APP_RANK_IPHONE_ARTICLE_JSON);
                    app_rank_iphone_article.setArguments(bundle_iphoart);
                    return app_rank_iphone_article;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
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

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
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

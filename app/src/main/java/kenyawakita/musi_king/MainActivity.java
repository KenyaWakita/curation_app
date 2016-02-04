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
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;


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
    AnalyticsApplication application;

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
        textView.setText("ニュース");
        ImageView image = (ImageView) findViewById(R.id.tab1_icon);
        image.setImageResource(R.drawable.news);

        ActionBar.Tab tab2 =actionBar.newTab();
        tab2.setCustomView(R.layout.tab2);
        tab2.setTabListener(this);
        actionBar.addTab(tab2);
        TextView textView2 = (TextView) findViewById(R.id.tab2_title);
        textView2.setText("掲示板");
        ImageView image2 = (ImageView) findViewById(R.id.tab2_icon);
        image2.setImageResource(R.drawable.board);


        ActionBar.Tab tab3 =actionBar.newTab();
        tab3.setCustomView(R.layout.tab3);
        tab3.setTabListener(this);
        actionBar.addTab(tab3);
        TextView textView3 = (TextView) findViewById(R.id.tab3_title);
        textView3.setText("攻略情報");
        ImageView image3 = (ImageView) findViewById(R.id.tab3_icon);
        image3.setImageResource(R.drawable.cheat);


        ActionBar.Tab tab4 =actionBar.newTab();
        tab4.setCustomView(R.layout.tab4);
        tab4.setTabListener(this);
        actionBar.addTab(tab4);
        TextView textView4 = (TextView) findViewById(R.id.tab4_title);
        textView4.setText("ゲーム実況");
        ImageView image4 = (ImageView) findViewById(R.id.tab4_icon);
        image4.setImageResource(R.drawable.game);

        ActionBar.Tab tab5 =actionBar.newTab();
        tab5.setCustomView(R.layout.tab5);
        tab5.setTabListener(this);
        actionBar.addTab(tab5);
        TextView textView5 = (TextView) findViewById(R.id.tab5_title);
        textView5.setText("武器");
        ImageView image5 = (ImageView) findViewById(R.id.tab5_icon);
        image5.setImageResource(R.drawable.weapon);

        ActionBar.Tab tab6 =actionBar.newTab();
        tab6.setCustomView(R.layout.tab6);
        tab6.setTabListener(this);
        actionBar.addTab(tab6);
        TextView textView6 = (TextView) findViewById(R.id.tab6_title);
        textView6.setText("モンスター");
        ImageView image6 = (ImageView) findViewById(R.id.tab6_icon);
        image6.setImageResource(R.drawable.monster);
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
                    application.tracker.setScreenName("ニュース");
                    application.tracker.send(new HitBuilders.ScreenViewBuilder().build());
                    NewsFragment news = new NewsFragment();
                    return news;
                case 1:
                    application.tracker.setScreenName("掲示板");
                    application.tracker.send(new HitBuilders.ScreenViewBuilder().build());
                    return new BoardFragment();
                case 2:
                    application.tracker.setScreenName("攻略情報");
                    application.tracker.send(new HitBuilders.ScreenViewBuilder().build());
                    BoardTypeFragment cheat = new BoardTypeFragment();
                    Bundle bundle_cheat = new Bundle();
                    bundle_cheat.putString("json_url", Constants.BoardType_JSON2);
                    cheat.setArguments(bundle_cheat);
                    return cheat;
                case 3:
                    application.tracker.setScreenName("動画実況");
                    application.tracker.send(new HitBuilders.ScreenViewBuilder().build());
                    YoutubeFragment youtube_jikkyou = new YoutubeFragment();
                    Bundle bundle_jikkyou = new Bundle();
                    bundle_jikkyou.putString("url", Constants.YOUTUBE_SEARCH_URL);

                    youtube_jikkyou.setArguments(bundle_jikkyou);
                    return youtube_jikkyou;
                case 4:
                    application.tracker.setScreenName("武器");
                    application.tracker.send(new HitBuilders.ScreenViewBuilder().build());
                    BoardTypeFragment weapon = new BoardTypeFragment();
                    Bundle bundle_weapon = new Bundle();
                    bundle_weapon.putString("json_url", Constants.BoardType_JSON3);
                    weapon.setArguments(bundle_weapon);
                    return weapon;
                case 5:
                    application.tracker.setScreenName("モンスター");
                    application.tracker.send(new HitBuilders.ScreenViewBuilder().build());
                    BoardTypeFragment monster = new BoardTypeFragment();
                    Bundle bundle_monster = new Bundle();
                    bundle_monster.putString("json_url", Constants.BoardType_JSON4);
                    monster.setArguments(bundle_monster);
                    return monster;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 6;
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
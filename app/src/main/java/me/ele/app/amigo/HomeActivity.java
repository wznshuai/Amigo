package me.ele.app.amigo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rey.material.widget.TabPageIndicator;

import me.ele.amigo.Amigo;
import me.ele.app.amigo.dev.DevActivity;
import me.ele.demo.A;


public class HomeActivity extends BaseActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();

    private TextView infoView;

    TextView metaDataView;

    TabPageIndicator indicator;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        infoView = (TextView) findViewById(R.id.info);
        metaDataView = (TextView) findViewById(R.id.meta_data);
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        pager = (ViewPager) findViewById(R.id.pager);
        Log.e(TAG, "onCreate");
        Log.e(TAG, "getApplication from HomeActivity-->" + getApplication());
        Log.e(TAG, "version code from host-->" + Amigo.getHostPackageInfo(this, 0).versionCode);

        infoView.setText(A.getDes());

        try {
            String metaData = getPackageManager().getApplicationInfo(getPackageName(),
                    PackageManager.GET_META_DATA).metaData.getString("data_key");
            metaDataView.setText("metaData:" + metaData);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView textView = new TextView(container.getContext());
                textView.setText("asdada");
                container.addView(textView);
                return textView;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "啊啊啊";
            }
        });

        indicator.setViewPager(pager);
    }

    public void gotoDemo(View v) {
        startActivity(new Intent(this, DemoActivity.class));
    }

    public void gotoDev(View v) {
        startActivity(new Intent(this, DevActivity.class));
    }
}

package com.xinran.testannotation;


import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.xinran.testannotation.qxannotation.InjectQxParse;
import com.xinran.testannotation.qxannotation.QxParseActivitysURl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
@QxParseActivitysURl(key="mainOne",url="qx://w/r")
public class MainActivity extends AppCompatActivity {
    static {
        InjectQxParse.PARSE_URL.parseUrl(MainActivity.class);
    }
    private static final String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";
    @Bind(R.id.tv)
    TextView mTv;
    List<String>  mList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        readManifest();
        getActivityUrls();

    }

    private void readManifest() {

        try {
            PackageInfo packageInfo=getPackageManager().getPackageInfo("com.xinran.testannotation", PackageManager.GET_ACTIVITIES);
            ActivityInfo[] infos = packageInfo.activities;
            for(int i=0;i<infos.length;i++){
//               mList.add(infos[i].name);
                try {
                    Class.forName(infos[i].name);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
//            mTv.setText(mList.get(1));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getActivityUrls() {
        Map<String, String> map=InjectQxParse.PARSE_URL.getActivityUrlMap();
        if(!map.isEmpty()){
            StringBuilder sb=new StringBuilder();
            for(Map.Entry<String,String> entry:map.entrySet()){
                sb.append(entry.getKey()).append("=").append(entry.getValue());
            }
            mTv.setText(sb.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.xinran.testannotation.qxannotation;

import android.app.Activity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qixinh on 16/4/15.
 */
public enum InjectQxParse {
    PARSE_URL;
    private static final Class<?>[] HALT_CLASSES = {Activity.class};
    private Map<String,String> maps=new HashMap<String,String>();

    public void parseUrl(Class<? extends Activity> cls) {
        if (cls == null) return;
        inject(cls);
    }


    private void inject(Class<? extends Activity> clazz) {

        if (clazz.isAnnotationPresent(QxParseActivitysURl.class)) {
            QxParseActivitysURl from = clazz.getAnnotation(QxParseActivitysURl.class);
            String key = from.key();
            String url = from.url();
            if(!key.equals("")&&!url.equals("")){
                maps.put(key,url);
            }

        }
    }
    public Map<String,String> getActivityUrlMap(){
        if(maps.isEmpty()) return Collections.EMPTY_MAP;
        return maps;
    }
}

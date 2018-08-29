package lookey.com.pickyou;

import android.app.Application;
import android.os.Build;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * Created by Lookey on 2018/8/29.
 * description:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initBugly();
    }

    private void initBugly() {
        Beta.autoCheckUpgrade=false;
        Bugly.setUserId(this, Build.SERIAL);//bugly用户id
        Bugly.init(getApplicationContext(),"197d952156", true);
    }
}

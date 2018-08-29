package lookey.com.pickyou;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dalong.marqueeview.MarqueeView;
import com.gyf.barlibrary.ImmersionBar;
import com.tencent.bugly.beta.Beta;

import java.lang.ref.WeakReference;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    public MarqueeView tvName;
    public TextView tvName2;
    private EditText etInput;
    private TextView tvConfirm;
    private CheckBox checkBox;
    public int[] colors={Color.RED,Color.YELLOW,Color.GREEN,Color.BLUE,Color.CYAN,Color.MAGENTA};
//    public MyHandler myHandler=new MyHandler(new WeakReference<MainActivity>(this));
    public int screenWidth;
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Beta.checkUpgrade();
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.transparentBar().init();
        checkBox=findViewById(R.id.cb_gun);
        tvName=findViewById(R.id.tv_name);
        etInput=findViewById(R.id.et_input);
        tvConfirm=findViewById(R.id.tv_confirm);
        tvName2=findViewById(R.id.tv_name2);

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputstr=etInput.getText().toString();
                if(TextUtils.isEmpty(inputstr)){
                    Toast.makeText(MainActivity.this,"请输入显示文字",Toast.LENGTH_SHORT).show();
                    return;
                }
                    tvName.setText(inputstr+"   "+inputstr+"   "+inputstr+"   "+inputstr+"   "+inputstr+"   "+inputstr+"   "+inputstr+"   "+inputstr+"   "+inputstr+"   "+inputstr);
                    tvName2.setText(inputstr);


                if(checkBox.isChecked()||inputstr.length()>3){
                       switchVisiable(1);
                 }else {
                       switchVisiable(2);
                }

            }
        });

//        WindowManager manager = this.getWindowManager();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        manager.getDefaultDisplay().getMetrics(outMetrics);
//        int width = outMetrics.widthPixels-100;
//        tvName.setTextSize(width/3f);





//        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//
//                myHandler.sendEmptyMessage(10);
//            }
//        },500,500, TimeUnit.MILLISECONDS);
    }
    private void switchVisiable(int mode){
        switch (mode){
            case 0: //输入
                tvConfirm.setVisibility(View.VISIBLE);
                etInput.setVisibility(View.VISIBLE);
                checkBox.setVisibility(View.VISIBLE);
                tvName.stopScroll();
                tvName.setVisibility(View.GONE);
                tvName2.setVisibility(View.GONE);

                break;
            case 1://滚动
                tvConfirm.setVisibility(View.GONE);
                etInput.setVisibility(View.GONE);
                checkBox.setVisibility(View.GONE);

                tvName.setVisibility(View.VISIBLE);
                tvName2.setVisibility(View.GONE);

                tvName.startScroll();
                break;
            case 2://不滚动
                tvConfirm.setVisibility(View.GONE);
                etInput.setVisibility(View.GONE);
                checkBox.setVisibility(View.GONE);
                tvName.setVisibility(View.GONE);
                tvName2.setVisibility(View.VISIBLE);

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(etInput.getVisibility()==View.GONE){
                switchVisiable(0);
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mImmersionBar.destroy();
    }

//    static class MyHandler extends Handler{
//        WeakReference<MainActivity> mainActivityWeakReference;
//       public int indexColor=0;
//       public MyHandler(WeakReference<MainActivity> mainActivityWeakReference) {
//           this.mainActivityWeakReference = mainActivityWeakReference;
//
//
//       }
//
//       @Override
//       public void handleMessage(Message msg) {
//           super.handleMessage(msg);
//           MainActivity mainActivity=mainActivityWeakReference.get();
//           if(mainActivity==null){
//               return;
//           }
//            mainActivity.tvName.setTextColor(mainActivity.colors[indexColor%6]);
//           indexColor++;
//       }
//   }
}

package com.example.administrator.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.SupportMenuInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity {
    private String dd;
    private  Response response = null;
    private SlidingMenu menu;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){

                    Toast.makeText(MainActivity.this,"hello messss", Toast.LENGTH_SHORT).show();

                TextView textView=findViewById(R.id.text11);

                textView.setText(dd);
                try {
                    JSONObject jsonObject=new JSONObject(dd);
                   String name=jsonObject.get("pagenumber").toString();
                    textView.setText(dd);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++){
                        Log.i("json", ""+jsonArray.get(i));
                        JSONObject jsonObject1=(JSONObject)jsonArray.get(i);
                        Log.i("id", ""+jsonObject1.get("iduser"));

                    }

//                    for (int i=0;i<jsonArray.length();i++){
//                        JSONObject jsonObject1=(JSONObject)jsonArray.get(i);
//                        String pagenumber=jsonObject.getJSONArray("data");
//
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                   JSONArray jsonArray=new JSONArray(dd);
                   for (int i=0;i<jsonArray.length();i++){
                       JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                       Log.i("log", "handleMessage: "+jsonObject.get("iduser"));
                   }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }

    };

    public void getDatasync(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    RequestBody formBody=new FormBody.Builder()
                            .add("username","10")
                            .build();
                    Request request = new Request.Builder()
                            .url("http://192.168.1.104/index.php/git/data")//请求接口。如果需要传参拼接到接口后面。
                            .post(formBody)
                            .build();//创建Request 对象

                    response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        dd=response.body().string();
                        InetAddress inetAddress=InetAddress.getLocalHost();
                        Log.i("ip地址", "onCreate: "+inetAddress);

                        Log.d("kwwl","response.code1()=="+response.code());
                        Log.d("kwwl","response.message()=="+response.message());
                        Log.d("kwwl","res=="+dd);
                        //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                        Message message=new Message();
                        message.what=200;
//                        Bundle bundle=new Bundle();
//
//                        bundle.putString("body",response.body().string());
//                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("learnAndroid");
        toolbar.setSubtitle("Git");
        toolbar.setNavigationIcon(R.drawable.list);
        setSupportActionBar(toolbar);

//        DrawerLayout drawerLayout=findViewById(R.id.drawer);
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.START);
//        drawerLayout.openDrawer(Gravity.START);
//
//        SlidingMenu slidingMenu=new SlidingMenu(this);
//        //设置菜单的位置设置从左弹出sliding menu
//        slidingMenu.setMode(SlidingMenu.LEFT);
//        //设置触摸屏的模式（满屏幕）
//        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//
//        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
//
//     // slidingMenu.setShadowDrawable(R.color.colorAccent);
//
//        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        slidingMenu.setFadeDegree(0.35f);
//        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
//        slidingMenu.setMenu(R.layout.layout);
         menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.shadow);
        menu.setShadowDrawable(R.color.colorAccent);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.layout_left_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle(true);
            }
        });




getDatasync();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setIconEnable(menu,true);  //  就是这一句使图标能显示
        MenuInflater inflater=new MenuInflater(getApplicationContext());
        inflater.inflate(R.menu.optionmenu,menu);

        return true;
    }
    private void setIconEnable(Menu menu, boolean enable)
    {
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try{
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
        return true;
    }
}


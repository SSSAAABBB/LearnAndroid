package com.example.administrator.myapplication;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
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
        SlidingMenu menu = new SlidingMenu(this);
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

      GetExample example=new GetExample();
        String response= null;
        try {
            if ( example.run("https://raw.github.com/square/okhttp/master/README.md")!=null)
            {
                Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        TextView textView=findViewById(R.id.text11);
//        textView.setText(response);








    }
}


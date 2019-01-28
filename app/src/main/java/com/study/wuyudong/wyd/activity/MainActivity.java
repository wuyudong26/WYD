package com.study.wuyudong.wyd.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.socks.library.KLog;
import com.study.wuyudong.wyd.R;
import com.study.wuyudong.wyd.adapter.MainFragmentPagerAdapter;
import com.study.wuyudong.wyd.fragment.MainForthFragment;
import com.study.wuyudong.wyd.utils.MyLog;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener, View.OnTouchListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private PopupWindow popupWindow;
    private PopupMenu popupMenu;
    //UI Objects
    private TextView txt_topbar;
    private ImageView add;
    private ImageView search;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private RadioButton rb_message;
    private RadioButton rb_better;
    private RadioButton rb_setting;
    private ViewPager vpager;

    private MainFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_channel.setChecked(true);
        KLog.d(TAG,"MainActivity onCreate() excuted" );
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
        MyLog.d("MainActivity onCreate() excuted");
    }
    private void bindViews() {
        add=findViewById(R.id.add);
        search=findViewById(R.id.search);
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        rb_better = (RadioButton) findViewById(R.id.rb_better);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
        search.setOnTouchListener(this);
        search.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_channel:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_message:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_better:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_setting:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (i == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_channel.setChecked(true);
                    txt_topbar.setText("微信");
                    break;
                case PAGE_TWO:
                    rb_message.setChecked(true);
                    txt_topbar.setText("通讯录");
                    break;
                case PAGE_THREE:
                    rb_better.setChecked(true);
                    txt_topbar.setText("发现");
                    break;
                case PAGE_FOUR:
                    rb_setting.setChecked(true);
                    txt_topbar.setText("我");
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                showPopupMenu(add);
                break;
            case R.id.search:
                startActivity(new Intent(this,SearchActivity.class));
                break;
        }
    }
//    private void showPopMenu(View v) {
//        popupMenu=new PopupMenu(this,v);
//        popupMenu.inflate(R.menu.add);
//        popupMenu.show();
//    }

    public void showPopupMenu(View view)
    {
        //创建PopupMenu对象
        popupMenu=new PopupMenu(this,view);
        //将R.menu.popup_menu菜单资源加载到popup菜单中
        getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        //为popup菜单的菜单项单击事件绑定事件监听器
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId())
                {
//                    case R.id.exit:
//                        //隐藏该对话框
//                        popupMenu.dismiss();
//                        break;
                    default:
                        //使用Toast显示用户单击的菜单项
                        MyLog.d("您单击了【"+item.getTitle()+"】菜单项");
                        Toast.makeText(MainActivity.this, "您单击了【"+item.getTitle()+"】菜单项",Toast.LENGTH_SHORT).show();

                }
                // TODO Auto-generated method stub
                return false;
            }

        });
        popupMenu.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}

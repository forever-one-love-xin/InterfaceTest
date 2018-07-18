package com.example.dell.interfacetest.activitys;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.dell.interfacetest.R;
import com.example.dell.interfacetest.adapter.FruitAdapter;
import com.example.dell.interfacetest.datas.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 测试ToolBar
 * */
public class MaterialDesignTestActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Fruit[] fruits = {new Fruit ("Apple", "http://img3.duitang.com/uploads/item/201601/16/20160116210122_wHvLi.thumb.700_0.jpeg"), new Fruit ("Banana", "http://c.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=79b319355cafa40f3c93c6d99e542f79/d058ccbf6c81800a139b2709b73533fa828b47be.jpg"),
            new Fruit ("Orange", "http://img.mp.itc.cn/upload/20170413/99571862c2da4c2dba7814cebe9f1926_th.jpeg"), new Fruit ("Watermelon", "http://ol.tgbus.com/news/UploadFiles_2374/201303/20130329094423952.jpg"),
            new Fruit ("Pear", "http://imgsrc.baidu.com/forum/w=580/sign=b5b6d84a5d4e9258a63486e6ac83d1d1/24761146f21fbe09d7f6f33a60600c338644adb5.jpg"), new Fruit ("Grape", "http://08.imgmini.eastday.com/mobile/20180525/20180525041119_ab31bca9bc50b591c31aaaa3d09546cd_3.jpeg"),
            new Fruit ("Pineapple", "http://pic.duowan.com/webgame/1012/156161492228/156161713873.jpg"), new Fruit ("Strawberry", "http://img3.imgtn.bdimg.com/it/u=966836603,3416939048&fm=214&gp=0.jpg"),
            new Fruit ("Cherry", "http://p1.qhimgs4.com/t01843236df09df2eed.jpg"), new Fruit ("Mango", "http://i2.hdslb.com/bfs/archive/b259ad964c3dfb29c1e9435714ca9e0dd44bacc8.jpg")
    };
    private List<Fruit> fruitList = new ArrayList<> ();
    private FruitAdapter adapter;
    //下拉刷新
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.drawer_test1);

        initUI ();
        initFruits ();

        RecyclerView recyclerView = (RecyclerView) findViewById (R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager (this, 2);
        recyclerView.setLayoutManager (layoutManager);
        adapter = new FruitAdapter (fruitList);
        recyclerView.setAdapter (adapter);
        //初始化下拉刷新控件
        swipeRefresh = (SwipeRefreshLayout) findViewById (R.id.swipe_refresh);
        //设置刷新延迟颜色
        swipeRefresh.setColorSchemeResources (R.color.colorPrimary);
        //给下拉刷新添加监听事件
        swipeRefresh.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh() {
                refreshFruits ();
            }
        });

    }
    /*
    * 下拉刷新触发的事件
    * */
    private void refreshFruits() {
        new Thread (new Runnable () {
            @Override
            public void run() {
                try {
                    Thread.sleep (2000);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
                runOnUiThread (new Runnable () {
                    @Override
                    public void run() {
                        initFruits ();
                        adapter.notifyDataSetChanged ();
                        swipeRefresh.setRefreshing (false);
                    }
                });
            }
        }).start ();
    }


    public void initUI() {
        //使用Toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        ActionBar actionBar = getSupportActionBar ();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled (true);
            //            actionBar.setHomeAsUpIndicator (R.drawable.);
        }

        //使用DrawerLayout
        mDrawerLayout = findViewById (R.id.drawer_layout);
        /*使用NavigationView，使用之前还需要提前准备好两个东西：menu和headerLayout
        menu是用来在NavigationView中显示具体的菜单项的，
        headerLayout则是用来在NavigationView中显示头部布局的。*/
        NavigationView navigationView = findViewById (R.id.nav_view);
        //为menu栏默认选择nav_call项
        navigationView.setCheckedItem (R.id.nav_call);
        //为menu设置监听事件
        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers ();
                return true;
            }
        });

        //FloatingActionButton点击事件处理
        FloatingActionButton floatingActionButton = findViewById (R.id.fab);
        floatingActionButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                /*
                 *提示工具Snackbar并不是Toast的替代品，它们两者之间有着不同的应用场景。
                 *Toast的作用是告诉用户现在发生了什么事情，但同时用户只能被动接收这个事情，因为没有什么办法让用户进行选择。
                 *而Snackbar则在这方面进行了扩展，它允许在提示当中加入一个可交互按钮，当用户点击按钮的时候可以执行一些额外的逻辑操作。
                 */
                Toast.makeText (MaterialDesignTestActivity.this, "悬浮边框", Toast.LENGTH_SHORT).show ();
                //Snackbar的用法也非常简单，它和Toast是基本相似的，只不过可以额外增加一个按钮的点击事件。
                Snackbar.make (view, "Data delete", Snackbar.LENGTH_SHORT).
                        setAction ("Undo", new View.OnClickListener () {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText (MaterialDesignTestActivity.this, "带有操作的提示", Toast.LENGTH_SHORT).show ();
                            }
                        }).show ();
            }
        });
    }

    private void initFruits() {
        fruitList.clear ();
        for (int i = 0; i < 50; i++) {
            Random random = new Random ();
            int index = random.nextInt (fruits.length);
            fruitList.add (fruits[index]);
        }
    }

    //为ToolBar引入布局menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.toolbar, menu);
        return true;
    }

    //为ToolBar的menu按键设置点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.backup:
                Toast.makeText (this, "You", Toast.LENGTH_SHORT).show ();

                break;
        }

        return true;
    }
}

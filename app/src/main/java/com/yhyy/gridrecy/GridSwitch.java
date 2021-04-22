package com.yhyy.gridrecy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yhyy.gridrecy.adapter.Adapter_Switch;
import com.yhyy.gridrecy.inter.OnItemClickListener;
import com.yhyy.gridrecy.widget.RecyVertical;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridSwitch extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    Adapter_Switch adapterSwitch;

    GridLayoutManager gridLayoutManager;

    List<String> stringList = new ArrayList<>();

    boolean end = true;

    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_switch);
        ButterKnife.bind(this);
        initData();
        initToolBar();
        initListener();
    }

    private void initListener() {
        gridLayoutManager = new GridLayoutManager(context, 2, RecyclerView.VERTICAL, false);
        recyclerview.setLayoutManager(gridLayoutManager);
        adapterSwitch = new Adapter_Switch(context, stringList);
        if (stringList.size() == 0) {
            adapterSwitch.changeMoreStatus(Adapter_Switch.NOTMORE);
        } else {
            adapterSwitch.changeMoreStatus(Adapter_Switch.LOADMORE);
        }
        recyclerview.setAdapter(adapterSwitch);
        recyclerview.addOnScrollListener(new RecyVertical() {
            @Override
            public void onLoadMore() {
                if (end) {
                    adapterSwitch.changeMoreStatus(Adapter_Switch.LOADING);
                    end = false;
                    adapterSwitch.AddFooterItem(stringList);
                } else {
                    adapterSwitch.changeMoreStatus(Adapter_Switch.NOTMORE);
                }
            }
        });
        adapterSwitch.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Integer integer) {
                Toast.makeText(context, "第" + (integer + 1) + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        stringList.clear();
        for (int i = 0; i < 20; i++) {
            String a = "123";
            String b = "123456";
            String c = "123456789";
            if (i % 3 == 0) {
                stringList.add(a);
            } else if (i % 3 == 1) {
                stringList.add(b);
            } else if (i % 3 == 2) {
                stringList.add(c);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //导入菜单布局
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.change:
                if (type == 0) {
                    gridLayoutManager.setSpanCount(1);//设为1列
                    adapterSwitch.switchStyle(true);
                    type = 1;
                } else {
                    gridLayoutManager.setSpanCount(2);//设为2列
                    adapterSwitch.switchStyle(false);
                    type = 0;
                }
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.yhyy.gridrecy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yhyy.gridrecy.adapter.Adapter_Custom;
import com.yhyy.gridrecy.adapter.Adapter_Switch;
import com.yhyy.gridrecy.inter.OnItemClickListener;
import com.yhyy.gridrecy.widget.RecyVertical;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridCustom extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    Adapter_Custom adapterCustom;

    GridLayoutManager gridLayoutManager;

    List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_custom);
        ButterKnife.bind(this);
        initData();
        initToolBar();
        initListener();
    }

    private void initListener() {
        gridLayoutManager = new GridLayoutManager(context, 3, RecyclerView.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position < 3) {
                    return position + 1;
                } else if (position ==3) {
                    return 3;
                } else if (position ==4) {
                    return 1;
                } else {
                    return 2;
                }
            }
        });
        recyclerview.setLayoutManager(gridLayoutManager);
        adapterCustom = new Adapter_Custom(context, stringList);
        recyclerview.setAdapter(adapterCustom);
        adapterCustom.setOnItemClickListener(new OnItemClickListener() {
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
        for (int i = 0; i < 6; i++) {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
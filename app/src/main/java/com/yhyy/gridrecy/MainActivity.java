package com.yhyy.gridrecy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yhyy.gridrecy.adapter.Adapter_Switch;
import com.yhyy.gridrecy.adapter.Adapter_Type;
import com.yhyy.gridrecy.config.Contracts;
import com.yhyy.gridrecy.inter.OnItemClickListener;
import com.yhyy.gridrecy.widget.RecyVertical;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    Adapter_Type adapterType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initToolBar();
        initListener();
    }

    private void initListener() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        adapterType = new Adapter_Type(context, Contracts.typeList);
        recyclerview.setAdapter(adapterType);
        adapterType.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Integer integer) {
                switch (integer) {
                    case 0:
                        startActivity(new Intent(context, GridSwitch.class));
                        break;
                    case 1:
                        startActivity(new Intent(context, GridCustom.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initToolBar() {
    }

    private void initData() {

    }
}
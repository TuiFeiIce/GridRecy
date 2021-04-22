package com.yhyy.gridrecy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.yhyy.gridrecy.R;
import com.yhyy.gridrecy.inter.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_Custom extends RecyclerView.Adapter {
    public OnItemClickListener onItemClickListener;
    public LayoutInflater layoutInflater;
    private Context context;
    private List<String> stringList;

    public Adapter_Custom(Context context, List<String> datas) {
        this.context = context;
        this.stringList = datas;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GridHolder(layoutInflater.inflate(R.layout.recy_vertical, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GridHolder) {
            GridHolder gridHolder = (GridHolder) holder;
            gridHolder.tvItemName.setText(stringList.get(position));
            gridHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(gridHolder.itemView, position);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class GridHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_name)
        TextView tvItemName;

        public GridHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
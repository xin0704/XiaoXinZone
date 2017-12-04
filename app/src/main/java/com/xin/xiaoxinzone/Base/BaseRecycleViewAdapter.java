package com.xin.xiaoxinzone.Base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/11/15.
 */

public abstract class BaseRecycleViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public OnItemClickListener listener;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return getAdapterItemCount();
    }

    public abstract VH onCreateHolder(ViewGroup parent, int viewType);

    public abstract void onBindHolder(VH holder, int position);

    public abstract int getAdapterItemCount();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}

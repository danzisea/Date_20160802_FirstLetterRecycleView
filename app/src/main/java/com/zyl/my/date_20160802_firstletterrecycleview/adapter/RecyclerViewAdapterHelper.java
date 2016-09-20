package com.zyl.my.date_20160802_firstletterrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by My on 2016/8/2.
 */
public abstract class RecyclerViewAdapterHelper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    protected Context mContext = null;
    protected LayoutInflater inflater = null;
    protected List<T> list = null;

    public RecyclerViewAdapterHelper(Context mContext,List<T> list){
        this.list = list;
        this.mContext = mContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateMyViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindMyViewHolder(holder,position);
    }

    public abstract RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }


    //全局刷新方法
    public void reLoadRecycleView(List<T> _list,boolean isClear){
        if(isClear){
           list.clear();
        }
        list.addAll(_list);
        notifyDataSetChanged();
    }

    //局部刷新方法调用
    //增加一条数据
    public void addItem(int position,T t){
        list.add(position, t);
        notifyItemInserted(position);
    }
    //增加多条数据
    public void addItems(int startPosition,List<T> _list){
        list.addAll(_list);
        notifyItemRangeInserted(startPosition, _list.size());
    }

    //删除一条数据
    public void reMovedItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    //删除多条数据
    public void reMovedItems(int startPosition,List<T> _list){
        list.removeAll(_list);
        notifyItemRangeRemoved(startPosition, _list.size());
    }

    //更新一条数据
    public void updateItem(int position,T t){
        list.set(position,t);
        notifyItemChanged(position);
    }
    //更新多条数据
    public void updateItems(int startPosition,List<T> _list){
        list.removeAll(_list);
        list.addAll(startPosition,_list);
        notifyItemRangeChanged(startPosition,_list.size());
    }

}

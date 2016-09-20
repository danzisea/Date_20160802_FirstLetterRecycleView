package com.zyl.my.date_20160802_firstletterrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zyl.my.date_20160802_firstletterrecycleview.R;
import com.zyl.my.date_20160802_firstletterrecycleview.bean.UserBean;

import java.util.List;

/**
 * Created by My on 2016/8/2.
 */
public class MyAdapter extends RecyclerViewAdapterHelper<UserBean> implements SectionIndexer {
    public MyAdapter(Context mContext, List<UserBean> list) {
        super(mContext, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recyclerview, parent, false);
        return new MyViewHoldler(view);
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHoldler myHoldler  = (MyViewHoldler) holder;
        myHoldler.textView_item_username.setText(list.get(position).getUserName());
        myHoldler.textView_item_pinyin.setText(list.get(position).getPinyin());
        Glide.with(mContext)
             .load(list.get(position).getIconUrl())
             .placeholder(R.mipmap.ic_stub)
             .error(R.mipmap.ic_empty)
             .into(myHoldler.imageView_item_icon);
        //设置第一个字母出现的位置
        int sectionIndex = getSectionForPosition(position);
        int fristLetterposition = getPositionForSection(sectionIndex);
        if(position == fristLetterposition){
            myHoldler.textView_item_firstletter.setVisibility(View.VISIBLE);
            myHoldler.textView_item_firstletter.setText(list.get(position).getFristLetter());
        }else{
            myHoldler.textView_item_firstletter.setText("");
            myHoldler.textView_item_firstletter.setVisibility(View.GONE);
        }
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < list.size(); i++) {
            String fristLetter = list.get(i).getFristLetter();
            if(sectionIndex == fristLetter.charAt(0)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getFristLetter().charAt(0);
    }

    public class MyViewHoldler extends RecyclerView.ViewHolder {
        private TextView textView_item_username;
        private TextView textView_item_pinyin;
        private TextView textView_item_firstletter;
        private ImageView  imageView_item_icon;

        public MyViewHoldler(View itemView) {
            super(itemView);
            textView_item_username = (TextView) itemView.findViewById(R.id.textView_item_username);
            textView_item_pinyin = (TextView) itemView.findViewById(R.id.textView_item_pinyin);
            imageView_item_icon = (ImageView) itemView.findViewById(R.id.imageView_item_icon);
            textView_item_firstletter = (TextView) itemView.findViewById(R.id.textView_item_firstletter);

        }
    }
}
package com.zyl.my.date_20160802_firstletterrecycleview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zyl.my.date_20160802_firstletterrecycleview.Helper.ChineseToPinyinHelper;
import com.zyl.my.date_20160802_firstletterrecycleview.adapter.MyAdapter;
import com.zyl.my.date_20160802_firstletterrecycleview.bean.UserBean;
import com.zyl.my.date_20160802_firstletterrecycleview.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView_main;
    private Context mContext = this;
    private MyAdapter adapter = null;
    private List<UserBean> totalList = new ArrayList<>();
    private CustomView customView_main;
    private TextView textview_main_letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        String[] arrUsernames = getResources().getStringArray(R.array.arrUsernames);
        String[] arrIconUrl = getResources().getStringArray(R.array.arrIconUrl);
        for (int i = 0; i < arrUsernames.length; i++) {
            UserBean userBean = new UserBean();
            String username = arrUsernames[i];
            String pinyin = "";
            String firstLetter = "";

            if(username.matches("^[\u4e00-\u9fa5]+")){
                pinyin = ChineseToPinyinHelper.getInstance().getPinyin(username);
                firstLetter = pinyin.substring(0, 1).toUpperCase();
            }else if(username.matches("^[A-Za-z]+")){
                pinyin = username;
                firstLetter = pinyin.substring(0,1).toUpperCase();
            }else {
                pinyin = "#"+username;
                firstLetter = pinyin.substring(0, 1);
            }
            userBean.setUserName(username);
            userBean.setIconUrl(arrIconUrl[i]);
            userBean.setPinyin(pinyin);
            userBean.setFristLetter(firstLetter);
            totalList.add(userBean);
        }

        //排序：
        Collections.sort(totalList, new Comparator<UserBean>() {
            @Override
            public int compare(UserBean lhs, UserBean rhs) {
                return lhs.getPinyin().toLowerCase().compareTo(rhs.getPinyin().toLowerCase());//必须转为小写，一致了
            }
        });
    }

    private void initView() {
        customView_main = (CustomView) findViewById(R.id.customView_main);
        textview_main_letter = (TextView) findViewById(R.id.textview_main_letter);
        recyclerView_main = (RecyclerView) findViewById(R.id.recyclerView_main);
        recyclerView_main.setHasFixedSize(true);
        recyclerView_main.setItemAnimator(new DefaultItemAnimator());
        recyclerView_main.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView_main.setLayoutManager(manager);
        adapter = new MyAdapter(mContext,totalList);
        recyclerView_main.setAdapter(adapter);

        customView_main.setOnLetterclickListener(textview_main_letter, new CustomView.OnLetterClickListener() {
            @Override
            public void LetterClick(String letter) {//当点击这个view时滚到这个组的第一个位置
                int fristposition = adapter.getPositionForSection(letter.charAt(0));
                recyclerView_main.scrollToPosition(fristposition);
            }
        });
    }


}

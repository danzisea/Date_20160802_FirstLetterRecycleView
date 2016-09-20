package com.zyl.my.date_20160802_firstletterrecycleview.bean;

/**
 * Created by My on 2016/8/2.
 */
public class UserBean {
    private String UserName;
    private String pinyin;
    private String fristLetter;
    private String iconUrl;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFristLetter() {
        return fristLetter;
    }

    public void setFristLetter(String fristLetter) {
        this.fristLetter = fristLetter;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}

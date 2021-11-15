package com.xiaoai.crawler.mvdates;

import com.alibaba.fastjson.annotation.JSONField;
import com.sun.xml.internal.ws.developer.Serialization;

import java.io.Serializable;

/**
 * @Author
 * @Date 2021-03-15 00:32
 */
public class MvBean implements Serializable{

    @JSONField(name = "id")
    private Integer id;
    @JSONField(name = "cover")
    private String cover;
    @JSONField(name = "briefDesc")
    private String briefDesc;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "playCount")
    private Integer playCount;
    @JSONField(name = "artistName")
    private String artistName;

    @JSONField(serialize = false)
    private String mvUrl;

    public String getMvUrl() {
        return mvUrl;
    }

    public void setMvUrl(String mvUrl) {
        this.mvUrl = mvUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "MvBean{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", briefDesc='" + briefDesc + '\'' +
                ", name='" + name + '\'' +
                ", playCount=" + playCount +
                ", artistName='" + artistName + '\'' +
                ", mvUrl='" + mvUrl + '\'' +
                '}';
    }
}

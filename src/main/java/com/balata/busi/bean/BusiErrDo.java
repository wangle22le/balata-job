package com.balata.busi.bean;

public class BusiErrDo {
    private String datatime;
    private String name;

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    private String errmsg;

    @Override
    public String toString() {
        return "BusiErrDo{" +
                "datatime='" + datatime + '\'' +
                ", name='" + name + '\'' +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}

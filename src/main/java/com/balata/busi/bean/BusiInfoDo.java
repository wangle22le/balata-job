package com.balata.busi.bean;

public class BusiInfoDo {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String amt;

    private String status;

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BusiInfoDo{" +
                "name='" + name + '\'' +
                ", amt=" + amt +
                ", status='" + status + '\'' +
                '}';
    }
}

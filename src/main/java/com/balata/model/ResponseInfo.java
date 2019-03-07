package com.balata.model;

import java.io.Serializable;

/**
 * Created by ccb on 2017/6/27.
 */
public class ResponseInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String responseMsg = "";// 返回信息
    private String responseCode = "";// 返回代码
    //	private String responseContent = "";// 返回内容
    private String txnSeq = "";// 交易流水号
    private String oriSeq = "";// 原流水号
    private long startTime = 0;
    private long endTime = 0;
    private long costTime = 0;// 耗时

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getTxnSeq() {
        return txnSeq;
    }

    public void setTxnSeq(String txnSeq) {
        this.txnSeq = txnSeq;
    }

    public String getOriSeq() {
        return oriSeq;
    }

    public void setOriSeq(String oriSeq) {
        this.oriSeq = oriSeq;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

//	public String getResponseContent() {
//		return responseContent;
//	}
//
//	public void setResponseContent(String responseContent) {
//		this.responseContent = responseContent;
//	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ResponseInfo [responseMsg=");
        builder.append(responseMsg);
        builder.append(", responseCode=");
        builder.append(responseCode);
//		builder.append(", responseContent=");
//		builder.append(responseContent);
        builder.append(", txnSeq=");
        builder.append(txnSeq);
        builder.append(", oriSeq=");
        builder.append(oriSeq);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", endTime=");
        builder.append(endTime);
        builder.append(", costTime=");
        builder.append(costTime);
        builder.append("]");
        return builder.toString();
    }
}

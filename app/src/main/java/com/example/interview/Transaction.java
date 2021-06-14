package com.example.interview;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String mobile;
    private String txnid;

    public Transaction(String amount, String mobile, String txnid, String status, String message) {
        this.amount = amount;
        this.mobile = mobile;
        this.txnid = txnid;
        this.status = status;
        this.message = message;
    }

    private String status;
    private String message;
}

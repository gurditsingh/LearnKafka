package com.learn.customObject;

public class Report {

    public int id;
    public String receiver;
    public String sender;
    public String mags;

    public Report() {
    }

    public Report(int id, String receiver, String sender, String mags) {
        this.id = id;
        this.receiver = receiver;
        this.sender = sender;
        this.mags = mags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMags() {
        return mags;
    }

    public void setMags(String mags) {
        this.mags = mags;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", mags='" + mags + '\'' +
                '}';
    }
}

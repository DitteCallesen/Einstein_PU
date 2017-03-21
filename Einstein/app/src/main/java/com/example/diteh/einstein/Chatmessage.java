package com.example.diteh.einstein;

import java.util.Date;

/**
 * Created by Truong on 14.03.2017.
 */

public class Chatmessage {
    private String messageText;
    private String messageUser;
    private String messageTIme;
    private int iD;


    public Chatmessage(int iD, String messageText, String messageUser, String messageTIme) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.messageTIme = messageTIme;
        this.iD = iD;

    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }
    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageTIme() {
        return messageTIme;
    }

    public void setMessageTIme(String messageTIme) {
        this.messageTIme = messageTIme;
    }
}

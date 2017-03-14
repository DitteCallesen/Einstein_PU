package com.example.diteh.einstein;

import java.util.Date;

/**
 * Created by Truong on 14.03.2017.
 */

public class Chatmessage {
    private String messageText;
    private String messageUser;
    private long messageTIme;

    public Chatmessage(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;

        messageTIme = new Date().getTime();
    }

    public Chatmessage() {

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

    public long getMessageTIme() {
        return messageTIme;
    }

    public void setMessageTIme(long messageTIme) {
        this.messageTIme = messageTIme;
    }
}

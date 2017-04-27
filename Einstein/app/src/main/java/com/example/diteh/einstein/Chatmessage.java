package com.example.diteh.einstein;

/**
 * Created by Truong on 14.03.2017.
 */

// Class for how chatmessages are built up
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

    public String getMessageText() {
        return messageText;
    }


    public String getMessageUser() {
        return messageUser;
    }


    public String getMessageTIme() {
        return messageTIme;
    }
}

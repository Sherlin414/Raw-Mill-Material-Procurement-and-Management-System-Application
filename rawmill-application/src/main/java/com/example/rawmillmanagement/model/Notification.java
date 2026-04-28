package com.example.rawmillmanagement.model;

public abstract class Notification implements Runnable {

    protected String recipientName;
    protected String recipientContact;
    protected String message;
    protected String notificationType;

    public Notification(String recipientName, String recipientContact,
                        String message, String notificationType) {
        this.recipientName = recipientName;
        this.recipientContact = recipientContact;
        this.message = message;
        this.notificationType = notificationType;
    }

    public abstract void sendNotification();

    protected void showHeader() {
        System.out.println("----- " + notificationType + " NOTIFICATION -----");
        System.out.println("To   : " + recipientName);
        System.out.println("Via  : " + recipientContact);
    }

    @Override
    public void run() {
        sendNotification();
    }
}
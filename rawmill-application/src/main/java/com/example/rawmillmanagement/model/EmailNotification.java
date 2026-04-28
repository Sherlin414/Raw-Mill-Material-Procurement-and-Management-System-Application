package com.example.rawmillmanagement.model;

public class EmailNotification extends Notification {

    private String subject;
    private String senderEmail;
    private String priorityLevel;

    public EmailNotification(String recipientName, String recipientEmail,
                             String subject, String message, String priorityLevel) {
        super(recipientName, recipientEmail, message, "EMAIL");
        this.subject = subject;
        this.senderEmail = "rawmill.system@cementplant.com";
        this.priorityLevel = priorityLevel;
    }

    @Override
    public void sendNotification() {
        showHeader();
        System.out.println("From    : " + senderEmail);
        System.out.println("Subject : " + subject);
        System.out.println("Priority: " + priorityLevel);
        System.out.println("Message : " + message);
        System.out.println("Status  : Email sent successfully.");
        System.out.println("------------------------------------");
    }
}
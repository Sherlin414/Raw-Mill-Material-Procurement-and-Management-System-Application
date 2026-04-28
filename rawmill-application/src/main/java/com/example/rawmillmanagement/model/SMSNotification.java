package com.example.rawmillmanagement.model;
public class SMSNotification extends Notification {

    private String serviceProvider;
    private boolean deliveryStatus;

    public SMSNotification(String recipientName, String phoneNumber, String message) {
        super(recipientName, phoneNumber, message, "SMS");
        this.serviceProvider = "Airtel Gateway";
        this.deliveryStatus = false;
    }

    @Override
    public void sendNotification() {
        showHeader();
        System.out.println("Service Provider : " + serviceProvider);
        System.out.println("Message          : " + message);
        deliveryStatus = true;
        System.out.println("Status           : SMS delivered successfully.");
        System.out.println("------------------------------------");
    }
}
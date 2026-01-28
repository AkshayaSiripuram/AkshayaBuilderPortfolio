package com.builderportfolio.model;

// Represents a client associated with a project
public class Client {

    static long clientLastId = 0; // tracks last assigned client ID for auto-increment

    private Long clientId;
    private String clientName;
    private String clientEmail;
    private String clientPhNumber;

    // Constructor assigns unique clientId automatically
    public Client(String clientName, String clientEmail, String clientPhNumber) {
        clientId = ++clientLastId; // auto-increment client ID
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientPhNumber = clientPhNumber;
    }

    public static Long getclientId() {
        return clientLastId;
    } // returns latest generated client ID

    public String getClientPhNumber() {
        return clientPhNumber;
    }

    public void setClientPhNumber(String clientPhNumber) {
        this.clientPhNumber = clientPhNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "Client{" + "clientId=" + clientId + ", clientName='" + clientName + '\'' + ", clientEmail='" + clientEmail + '\'' + ", clientPhNumber='" + clientPhNumber + '\'' + '}';
    }
}

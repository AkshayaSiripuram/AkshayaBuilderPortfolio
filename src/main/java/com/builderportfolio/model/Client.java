package com.builderportfolio.model;

/**
 * Represents a client associated with a project.
 * <p>
 * Each client has a unique client ID that is auto-incremented for every new instance.
 * The class stores the client's name, email, and phone number.
 * </p>
 */
public class Client {

    /** Tracks the last assigned client ID for auto-increment purposes */
    static long clientLastId = 0;

    private Long clientId;
    private String clientName;
    private String clientEmail;
    private String clientPhNumber;

    /**
     * Constructs a new Client with the specified name, email, and phone number.
     * <p>
     * The client ID is automatically assigned by incrementing the last generated ID.
     * </p>
     *
     * @param clientName   the name of the client
     * @param clientEmail  the email of the client
     * @param clientPhNumber the phone number of the client
     */
    public Client(String clientName, String clientEmail, String clientPhNumber) {
        clientId = ++clientLastId; // auto-increment client ID
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientPhNumber = clientPhNumber;
    }

    /**
     * Returns the latest generated client ID.
     *
     * @return the most recent client ID
     */
    public static Long getclientId() {
        return clientLastId;
    }

    /**
     * Returns the phone number of the client.
     *
     * @return the client's phone number
     */
    public String getClientPhNumber() {
        return clientPhNumber;
    }

    /**
     * Updates the client's phone number.
     *
     * @param clientPhNumber the new phone number to set
     */
    public void setClientPhNumber(String clientPhNumber) {
        this.clientPhNumber = clientPhNumber;
    }

    /**
     * Returns the email of the client.
     *
     * @return the client's email
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * Updates the client's email.
     *
     * @param clientEmail the new email to set
     */
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    /**
     * Returns the name of the client.
     *
     * @return the client's name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Updates the client's name.
     *
     * @param clientName the new name to set
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Returns a string representation of the client, including ID, name, email, and phone number.
     *
     * @return string representation of the client
     */
    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientPhNumber='" + clientPhNumber + '\'' +
                '}';
    }
}

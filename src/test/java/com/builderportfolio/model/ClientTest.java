package com.builderportfolio.model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientTest {

    private static Client client;

    @Test
    @Order(1)
    void testClientCreation() {

        client = new Client("Akshaya", "akshaya@gmail.com", "1234567890");

        assertNotNull(client);
        assertEquals(1L, Client.getclientId());
        assertEquals("Akshaya", client.getClientName());
        assertEquals("akshaya@gmail.com", client.getClientEmail());
        assertEquals("1234567890", client.getClientPhNumber());
    }

    @Test
    @Order(2)
    void testSettersAndGetters() {

        client.setClientName("Akshaya R");
        client.setClientEmail("akshaya.r@gmail.com");
        client.setClientPhNumber("9999999999");

        assertEquals("Akshaya R", client.getClientName());
        assertEquals("akshaya.r@gmail.com", client.getClientEmail());
        assertEquals("9999999999", client.getClientPhNumber());
    }

    @Test
    @Order(3)
    void testToString() {

        String clientString = client.toString();

        assertTrue(clientString.contains("clientId=1"));
        assertTrue(clientString.contains("Akshaya R"));
        assertTrue(clientString.contains("akshaya.r@gmail.com"));
        assertTrue(clientString.contains("9999999999"));
    }

    @Test
    @Order(4)
    void testAutoIncrementClientId() {

        Client secondClient = new Client("Ravi", "ravi@gmail.com", "8888888888");

        assertEquals(2L, Client.getclientId());
    }

    @Test
    @Order(5)
    void testSettersWithNullAndEmpty() {
        client.setClientName(null);
        client.setClientEmail("");
        client.setClientPhNumber(null);

        assertNull(client.getClientName());
        assertEquals("", client.getClientEmail());
        assertNull(client.getClientPhNumber());
    }

    @Test
    @Order(6)
    void testMultipleClientIdsAutoIncrement() {
        Client c1 = new Client("A", "a@gmail.com", "1111111111");
        Client c2 = new Client("B", "b@gmail.com", "2222222222");

        // Static counter should continue incrementing
        assertEquals(Client.getclientId(), c2.getclientId());
    }
}

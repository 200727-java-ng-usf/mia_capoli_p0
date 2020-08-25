package com.revature.util;

import com.revature.util.ConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class ConnectionFactoryTest {

    private ConnectionFactory connectionFactory;

    @Before
    public void setup() {
        connectionFactory = ConnectionFactory.getConnFactory();
    }

    @After
    public void tearDown() {
        connectionFactory = null;
    }

    @Test
    public void testForValidConFactory() {
        ConnectionFactory c1 = ConnectionFactory.getConnFactory();
        ConnectionFactory c2 = ConnectionFactory.getConnFactory();

        assertSame(c1, c2);

    }

    @Test
    public void ensureThatAConnectionIsObtained() {
        Connection conn = ConnectionFactory.getConnFactory().getConnection();
        assertNotNull(conn);
    }

}

package model.config;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 16/4/1.
 * it's the testing code for connection config
 */
public class ConnectionConfigTest {

    @Test
    public void testLoadConfig() throws Exception {
        ConnectionConfig connectionConfig = ConnectionConfig.getConnectionConfig();
        assertEquals("127.0.0.1", connectionConfig.getIp());
        assertEquals(9090, connectionConfig.getPort());
        assertEquals(true, connectionConfig.isNeedEncrypt());
    }
}
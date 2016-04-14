package controller.user;

import controller.LogicTesting;
import org.junit.Test;
import service.net.SessionManager;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by xlo on 16/4/14.
 * it's the testing code for login app
 */
public class LoginAppTesting extends LogicTesting {

    @Test
    public void check_url() throws Exception {
        loginApp("test user");

        assertEquals("test user", SessionManager.getSessionManager().getUsername(socket));
    }
}

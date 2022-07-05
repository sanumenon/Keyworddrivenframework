package client;


import executionEngine.KeywordEngine;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class LoginToApplication {
    public KeywordEngine KeywordEngine;
    @Test
    public void Login() throws InterruptedException, FileNotFoundException {
        KeywordEngine = new KeywordEngine();
        KeywordEngine.startExecution("Login");
    }
}

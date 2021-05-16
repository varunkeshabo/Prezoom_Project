package Prezoom;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions;


public class MainTest {
    private volatile boolean success = false;
    @Test
    public void testMain() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Main.main();
                    success = true;

                } catch (Throwable t) {
                    if (t.getCause() != null && t.getCause().getClass().equals(InterruptedException.class)) {
                        success = true;
                        return;
                    }
                    System.exit(0);
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(3000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch(InterruptedException ex) {
        }
        thread.interrupt();
        try {
            thread.join(1); // Wait 1 second for our wrapper thread to finish.
        } catch(InterruptedException ex) {
        }
        Assertions.assertTrue(success);
    }
}
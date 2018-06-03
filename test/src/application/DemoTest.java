package src.application;

import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

class DemoTest extends TestCase {

    public static void main(String[] args) {
        Demo demo=new Demo(279,415,396);
        demo.setVisible(true);
    }
}
package com.sun.health.apigetway.base;

import org.junit.Test;

public class BaseTest {

    public String finalReturn() {
        try {
            return "try";
        } catch (Exception e) {
            return "catch";
        } finally {
            return "finally";
        }
    }

    @Test
    public void test1() {
        System.out.println(finalReturn());
    }

}

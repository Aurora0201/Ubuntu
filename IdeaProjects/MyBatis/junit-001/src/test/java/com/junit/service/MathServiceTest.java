package com.junit.service;

import org.junit.Assert;
import org.junit.Test;

public class MathServiceTest {
    private MathService ms = new MathService();
    @Test
    public void sumTest() {
        //get the actual value
        int act = ms.sum(1, 2);
        //set expected value
        int exp = 4;
        //verify
        Assert.assertEquals(exp,act);
    }
}

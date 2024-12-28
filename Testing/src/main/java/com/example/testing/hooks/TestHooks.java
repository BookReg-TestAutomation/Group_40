package com.example.testing.hooks;

import com.example.testing.utils.TestContext;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class TestHooks {

    @Before
    public void setUp() {
        TestContext.getInstance().reset();
    }

    @After
    public void tearDown() {
        TestContext.getInstance().reset();
    }
}
package com.hjt.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void helloMessageReturn() throws Exception {
        String hello = "hello";
        // ResultActions action = mvc.perform(get("/hello"));
    }
}

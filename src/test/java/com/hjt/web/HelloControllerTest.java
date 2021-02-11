package com.hjt.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void helloMessageReturn() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string(hello));
    }

    @Test
    public void helloResponseDtoReturn() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount))
                    .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("name").value(name))
           .andExpect(jsonPath("amount").value(amount));
    }
}

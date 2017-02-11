package com.github.woodylic.restut.controller;

import com.github.woodylic.restut.JsonUtil;
import com.github.woodylic.restut.dto.BaseResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:spring-web.xml")
public class HelloControllerTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void greeting() throws Exception
    {
        mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().string(is("{\"success\":true,\"result\":\"Hello World!\",\"errorMessage\":null}")));
    }

    @Test
    public void greeting_post() throws Exception
    {
        BaseResponse<String> param = new BaseResponse<String>();
        param.setErrorMessage("from mock");
        mockMvc.perform(post("/greeting_post").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(param))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                ;
    }

}
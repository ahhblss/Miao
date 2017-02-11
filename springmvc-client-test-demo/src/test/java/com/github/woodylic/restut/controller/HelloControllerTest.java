package com.github.woodylic.restut.controller;

import com.github.woodylic.restut.JsonUtil;
import com.github.woodylic.restut.dto.BaseResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:spring-web.xml")
public class HelloControllerTest
{
    static RestTemplate restTemplate;//发送请求的对象
    static String baseUri = "http://localhost:18080/";

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void greeting() throws Exception
    {
        //get test
        ResponseEntity<BaseResponse> result = restTemplate.getForEntity(baseUri+"/greeting", BaseResponse.class);
        System.out.println(result.getBody().getErrorMessage());
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    public void greeting_post() throws Exception
    {
        BaseResponse<String> param = new BaseResponse<String>();
        param.setErrorMessage("from client");
        ResponseEntity<BaseResponse> result = restTemplate.postForEntity(baseUri+"/greeting_post",param, BaseResponse.class);
        System.out.println(result.getBody().getErrorMessage());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
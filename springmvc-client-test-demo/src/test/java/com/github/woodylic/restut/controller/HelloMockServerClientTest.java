package com.github.woodylic.restut.controller;

import com.github.woodylic.restut.JsonUtil;
import com.github.woodylic.restut.dto.BaseResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withCreatedEntity;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by Administrator on 2017/2/11.
 */
public class HelloMockServerClientTest {

    private MockRestServiceServer mockServer;
    static RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        //模拟一个服务器
        mockServer = createServer(restTemplate);

    }

    @Test
    public void testGreeting(){
        BaseResponse<String> param = new BaseResponse<String>();
        param.setErrorMessage("from client");
        String paramJson = JsonUtil.toJson(param);
        String createdLocation = "http://localhost:18080/greeting"+"/"+1;

        //模拟服务器配置
        String  url= "http://localhost:18080/greeting";
        mockServer
                .expect(requestTo(url))  //验证请求URI,URI必须匹配某个Matcher/uri字符串/URI
                .andExpect(method(HttpMethod.POST)) //支持post请求
                .andRespond(withSuccess().contentType(MediaType.APPLICATION_JSON).body(paramJson)); //添加响应信息

        //请求
        ResponseEntity<BaseResponse> responseEntity = restTemplate.postForEntity(url,param,BaseResponse.class);



        assertEquals(param, responseEntity.getBody());

        mockServer.verify();
    }

}

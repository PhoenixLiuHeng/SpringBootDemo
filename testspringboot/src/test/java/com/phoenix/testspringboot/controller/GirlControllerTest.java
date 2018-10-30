package com.phoenix.testspringboot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;


/**
 * 测试GirlController中的方法的单元测试类(测试API方法)，与测试Service方法中的单元测试方法不同
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GirlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // 测试GirlController中girlList()方法的单元测试方法
    @Test
    public void girlList() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/girls")).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
}
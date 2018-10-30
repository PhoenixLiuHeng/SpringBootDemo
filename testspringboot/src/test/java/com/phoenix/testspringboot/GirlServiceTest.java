package com.phoenix.testspringboot;

import com.phoenix.testspringboot.pojo.Girl;
import com.phoenix.testspringboot.service.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试GirlService类中方法的单元测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

    @Autowired
    private GirlService girlService;


    // 测试GirlService中findOne方法的单元测试方法
    @Test
    public void findOneTest(){
        Girl girl = girlService.findOne(12);
        Assert.assertEquals(new Integer(16), girl.getAge());
    }
}

package com.phoenix.testspringboot.service;

import com.phoenix.testspringboot.enums.ResultEnum;
import com.phoenix.testspringboot.exception.GirlException;
import com.phoenix.testspringboot.pojo.Girl;
import com.phoenix.testspringboot.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    // 新增两个女孩（用于springboot的事务测试）
    @Transactional
    public void insertTwoGirl(){
        Girl girlA = new Girl();
        girlA.setAge(16);
        girlA.setCupSize("B");

        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setAge(18);
        girlB.setCupSize("CC");

        girlRepository.save(girlB);
    }

    // 获取女孩的年龄，并根据年龄返回不同的提示信息（用于统一的异常处理功能）
    public void getAge(Integer id)throws Exception{
        Girl girl = girlRepository.findById(id).get();
        int age = girl.getAge();
        if(age < 10){
//            throw new Exception("你还在上小学吧"); // java自带异常，只有提示信息，没有code属性
//            throw new GirlException(100,"你还在上小学吧"); // 自定义异常方式，添加了code属性
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL); // 对异常的提示信息用枚举的方式操作，防止出现重复，且便于维护
        }else if(age > 10 && age < 16){
//            throw new Exception("你可能在上初中"); // java自带异常，只有提示信息，没有code属性
//            throw new GirlException(101, "你可能在上初中"); // 自定义异常方式，添加了code属性
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL); // 对异常的提示信息用枚举的方式操作，防止出现重复，且便于维护
        }
    }


    // 根据id查询一个女孩（用于单元测试的方法）
    public Girl findOne(Integer id){
        return girlRepository.findById(id).get();
    }
}

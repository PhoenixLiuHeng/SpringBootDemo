package com.phoenix.testspringboot.controller;

import com.phoenix.testspringboot.pojo.Girl;
import com.phoenix.testspringboot.pojo.Result;
import com.phoenix.testspringboot.repository.GirlRepository;
import com.phoenix.testspringboot.service.GirlService;
import com.phoenix.testspringboot.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女孩（注解也可以用@GetMapping(value = "/girls")）
     * @return
     */
    @RequestMapping(value = "/girls", method = RequestMethod.GET)
    public List girlList(){
        return girlRepository.findAll();
    }

    /**
     * 新增女孩(也可以写成下面方法二的方式，直接把对象作为参数，两种方法效果一样)
     * @param cupSize
     * @param age
     * @return
     */
//    @PostMapping(value="/girls")
//    public Girl girlAdd(@RequestParam(value="cupSize") String cupSize, @RequestParam(value="age") Integer age){
//        Girl girl = new Girl();
//        girl.setAge(age);
//        girl.setCupSize(cupSize);
//        return girlRepository.save(girl);
//    }


    /**
     * 新增女孩(方法二)(@Valid用于对参数进行校验，在Girl类的参数上添加对应的校验规则和返回的提示信息)
     * @param girl
     * @return
     */
    @PostMapping(value="/girls")
    public Girl girlAdd(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            System.out.println("出错了----------------");
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }
        return girlRepository.save(girl);
    }

    /**
     * 新增女孩(方法二)(做同一结果处理后的方法)
     * @param girl
     * @return
     */
    @PostMapping(value="/girls/uniqueResult")
    public Result<Girl> girlAddUnique(@Valid Girl girl, BindingResult bindingResult){
        // 如果校验出错
        if(bindingResult.hasFieldErrors()){
            return ResultUtils.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.succcess(girlRepository.save(girl));
    }

    /**
     * 根据id查询单个女生
     * @param id
     * @return
     */
    @GetMapping(value="/girls/{id}")
    public Girl girlFindOne(@PathVariable(value="id") Integer id){
        return girlRepository.findById(id).get();
    }

    /**
     * 更新一个女孩
     * @param id
     * @param age
     * @return
     */
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable(value="id") Integer id, @RequestParam(value = "age") Integer age,
            @RequestParam(value = "cupSize") String cupSize){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
    }

    /**
     * 删除一个女孩
     * @param id
     */
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable(value = "id") Integer id){
        girlRepository.deleteById(id);
    }

    /**
     * 根据年龄查询女孩列表（JpaRepository中没有该方法，需要在GirlRepository中自己定义）
     * @param age
     * @return
     */
    @GetMapping(value="/girls/age/{age}")
    public List girlListByAge(@PathVariable(value="age") Integer age){
        return girlRepository.findByAge(age);
    }

    /**
     * 保存两个女孩(调用service，并做事务管理)
     */
    @PostMapping(value = "/girls/two")
    public void girlTwo(){
        girlService.insertTwoGirl();
    }

    /**
     * 获取女孩的年龄，并根据年龄返回不同的提示信息（用于统一的异常处理功能），Controller中抛出的异常会在ExceptionHandler中处理
     */
    @GetMapping(value="/girls/getAge/{id}")
    public void getAge(@PathVariable(value="id") Integer id) throws Exception{
        girlService.getAge(id);
    }
}

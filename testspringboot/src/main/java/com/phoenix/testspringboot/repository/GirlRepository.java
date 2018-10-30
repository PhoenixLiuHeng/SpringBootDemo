package com.phoenix.testspringboot.repository;


import com.phoenix.testspringboot.pojo.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlRepository extends JpaRepository<Girl, Integer> {

    /**
     * 根据年龄查询女孩(方法名必须是findBy...才可以)
     * @param age
     * @return
     */
    public List<Girl> findByAge(Integer age);
}

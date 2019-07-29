package com.leone.microservice.search.mapper;

import com.leone.microservice.search.entity.Phone;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PhoneMapper {

    int insert(Phone record);

    int insertSelective(Phone record);

    @Select("select * from t_phone")
    List<Phone> findAll();

}
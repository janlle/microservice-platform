package com.leone.microservice.search.controller;

import com.leone.microservice.common.Result;
import com.leone.microservice.search.entity.Phone;
import com.leone.microservice.search.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-27
 **/
@RestController
@RequestMapping("/api/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;


    @GetMapping("/list")
    public List<Phone> findAll() {
        return phoneService.findAllFromDB();
    }

    /**
     * 从数据库导入到ES
     *
     * @return
     */
    @GetMapping("/importAll")
    public Result<Integer> importAllList() {
        return Result.success(phoneService.importAll());
    }

    /**
     * 模糊搜索
     *
     * @param query
     * @param pageable
     * @return
     */
    @GetMapping("/page")
    public Result<Page<Phone>> search(@RequestParam String query, @PageableDefault Pageable pageable) {
        return Result.success(phoneService.search(query, pageable));
    }


}

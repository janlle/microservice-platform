package com.leone.microservice.search.service;

import com.leone.microservice.search.entity.Phone;
import com.leone.microservice.search.mapper.PhoneMapper;
import com.leone.microservice.search.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-29
 **/
@Service
public class PhoneService {

    @Autowired
    private PhoneMapper phoneMapper;

    @Autowired
    private PhoneRepository phoneRepository;

    /**
     * 从mysql数据库中加载所有商品数据
     *
     * @return
     */
    public List<Phone> findAllFromDB() {
        return phoneMapper.findAll();
    }

    /**
     * 从mysql中导入到ES
     *
     * @return
     */
    public Integer importAll() {
        // 从数据库查询所有商品
        List<Phone> productList = findAllFromDB();

        Iterable<Phone> products = phoneRepository.saveAll(productList);
        return productList.size();
    }


    /**
     * 分页查找
     *
     * @param keyword
     * @param pageable
     * @return
     */
    public Page<Phone> search(String keyword, Pageable pageable) {
        return phoneRepository.findByPhoneOrTitleOrName(keyword, keyword, keyword, pageable);
    }

}

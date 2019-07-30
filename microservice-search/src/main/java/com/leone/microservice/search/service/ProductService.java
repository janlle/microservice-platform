package com.leone.microservice.search.service;


import com.leone.microservice.search.entity.Product;
import com.leone.microservice.search.espo.ProductPO;
import com.leone.microservice.search.mapper.ProductMapper;
import com.leone.microservice.search.repository.ProductEsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-27
 **/
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductEsRepository productEsRepository;

    /**
     * 从mysql数据库中加载所有商品数据
     *
     * @return
     */
    public List<Product> loadAll() {
        return productMapper.list();
    }




    /**
     * 从ES删除商品
     *
     * @param productId
     * @return
     */
    public Long deleteFromEs(Long productId) {
        try {
            productEsRepository.deleteById(productId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
        return productId;
    }

    /**
     * 批量删除
     *
     * @param productIds
     * @return
     */
    public Integer deleteBatchFromEs(Set<Long> productIds) {
        if (!CollectionUtils.isEmpty(productIds)) {
            Set<ProductPO> productSet = new HashSet<>();
            productIds.forEach(e -> productSet.add(new ProductPO()));
            productEsRepository.deleteAll(productSet);
            return productSet.size();
        }
        return 0;
    }

    /**
     * 分页查找
     *
     * @param keyword
     * @param pageable
     * @return
     */
    public Page<ProductPO> search(String keyword, Pageable pageable) {
        return productEsRepository.findByProductNameAndProductTitleAndKeywords(keyword, keyword, keyword, pageable);
    }


    /**
     * 根据商品id创建商品
     *
     * @param productId
     * @return
     */
    public ProductPO create(Long productId) {
        Product product = productMapper.findOne(productId);
        if (ObjectUtils.isEmpty(product)) {
            ProductPO esProduct = new ProductPO();
            BeanUtils.copyProperties(product, esProduct);
            return productEsRepository.save(esProduct);
        }
        return null;
    }


    /**
     * @param count
     * @return
     */
    public Integer insert(Integer count) {
        for (int i = 0; i < count; i++) {
            Product product = new Product();
            int result = productMapper.insert(product);
        }
        return null;
    }

}

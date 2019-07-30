package com.leone.microservice.search.service;

import com.leone.microservice.search.entity.Product;
import com.leone.microservice.search.espo.ProductPO;
import com.leone.microservice.search.pojo.ProductListVO;
import com.leone.microservice.search.repository.ProductEsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-29
 **/
@Service
public class ProductEsService {

    @Autowired
    private ProductEsRepository productEsRepository;

    @Autowired
    private ProductService productService;



    /**
     * 从 mysql 数据库中加载所有商品数据到 ES 集群
     *
     * @return
     */
    public Integer importAllProduct(List<Product> products) {
            // 从数据库查询所有商品
            List<Product> productList = productService.loadAll();

            List<ProductPO> collect = productList.stream().map(e -> {
                ProductPO esProduct = new ProductPO();
                BeanUtils.copyProperties(e, esProduct);

                return esProduct;
            }).collect(Collectors.toList());

            productEsRepository.saveAll(collect);

            return productList.size();
    }


    /**
     * @param productPO
     * @return
     */
    public Integer save(ProductPO productPO) {

        return 1;
    }

    public Integer delete(Long productId) {

        return 1;
    }

    /**
     * @param productId
     * @return
     */
    public Integer findOne(Long productId) {

        return 1;
    }

    /**
     * @param product
     * @return
     */
    public Integer update(Product product) {

        return 1;
    }

    /**
     * @param keywords
     * @param pageable
     * @return
     */
    public Page<ProductListVO> search(String keywords, Pageable pageable) {

        return null;
    }




}

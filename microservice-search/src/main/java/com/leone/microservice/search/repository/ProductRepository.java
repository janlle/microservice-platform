package com.leone.microservice.search.repository;

import com.leone.microservice.search.espo.ProductPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-27
 **/
@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductPO, Long> {

    /**
     * 搜索查询
     *
     * @param productName  商品名称
     * @param productTitle 商品标题
     * @param keywords     商品关键字
     * @param page         分页信息
     * @return
     */
    Page<ProductPO> findByProductNameAndProductTitleAndKeywords(String productName, String productTitle, String keywords, Pageable page);

}

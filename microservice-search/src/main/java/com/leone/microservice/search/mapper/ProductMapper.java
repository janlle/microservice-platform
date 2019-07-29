package com.leone.microservice.search.mapper;

import com.leone.microservice.search.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-27
 **/
@Mapper
public interface ProductMapper {

    @Select("select * from t_product")
    List<Product> list();

    @Select("select * from t_product where product_id = #{productId}")
    Product findOne(Long productId);

    @Insert("insert into t_product (`brand_id`, `brand_name`, `keywords`, `picture`, `product_category_id`, `product_category_name`, `product_name`, `product_price`, `product_sale`, `product_sn`, `product_status`, `product_title`, `stock`)" +
            " values (#{p.brandId}, #{p.brandName}, #{p.keywords}, #{p.picture}, #{p.productCategoryId}, #{p.productCategoryName}, #{p.productName}, #{p.productPrice}, #{p.productSale}, #{p.productSn}, #{p.productStatus}, #{p.productTitle}, #{p.stock})")
    int insert(@Param("p") Product product);

    @Update("update t_product set name = #{p.name} where product_id = #{p.productId}")
    int update(@Param("p") Product product);

    @Delete("delete from t_product where product_id = #{productId}")
    int deleteById(Long productId);

    int insertSelective(Product product);

    int updateSelective(Product product);

}

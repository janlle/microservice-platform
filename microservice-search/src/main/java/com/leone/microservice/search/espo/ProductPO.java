package com.leone.microservice.search.espo;

import com.leone.microservice.search.entity.Attribute;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-27
 **/
@Document(indexName = "store", type = "product", shards = 1, replicas = 0)
public class ProductPO implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    private Long productId;

    // 商品分类名称
    private String categoryId;

    // 商品标题
    //@Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String title;

    // 商品名称
    //@Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String name;

    // 商品价格
    private Long price;

    // 搜索关键字
    //@Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String keywords;

    // 商品序列号
    //@Field(type = FieldType.Keyword)
    private String barcode;

    // 商标名称
    //@Field(type = FieldType.Keyword)
    private String brandName;

    // 商品销量
    private Integer stale;

    // 商品库存
    private Integer stock;

    // 属性列表
    private List<Attribute> attributeList;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getStale() {
        return stale;
    }

    public void setStale(Integer stale) {
        this.stale = stale;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }
}

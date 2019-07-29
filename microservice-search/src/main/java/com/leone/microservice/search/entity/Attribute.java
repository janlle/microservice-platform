package com.leone.microservice.search.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-27
 **/
public class Attribute implements Serializable {

    @Id
    private Long attributeId;

    // 属性名称
    @Field(type = FieldType.Keyword)
    private String attributeName;

    // 属性值
    @Field(type = FieldType.Keyword)
    private String attributeValue;

    // 属性参数 0：规格，1：参数
    private Byte type;

    private Integer productId;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}

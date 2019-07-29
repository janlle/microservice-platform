package com.leone.microservice.search.controller;

import com.leone.microservice.common.Result;
import com.leone.microservice.search.espo.ProductPO;
import com.leone.microservice.search.entity.Product;
import com.leone.microservice.search.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-27
 **/
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    // ------------- 商品的CRUD -------------

    @GetMapping("/list")
    public List<Product> list() {
        return productService.loadAll();
    }

    @GetMapping("/page")
    public List<Product> page() {
        return productService.loadAll();
    }

    @PostMapping
    public Result<Integer> insert(Integer count) {
        return Result.success(productService.insert(count));
    }

    @PutMapping
    public Result<Integer> update(Integer count) {
        return Result.success(productService.insert(count));
    }

    @DeleteMapping
    public Result<Integer> delete(Integer count) {
        return Result.success(productService.insert(count));
    }

    @GetMapping
    public Result<Integer> get(Integer count) {
        return Result.success(productService.insert(count));
    }


    // ------------- ES商品查询 -------------

    @GetMapping("/es/import")
    public Result<Integer> importAllList() {
        return Result.success(productService.importAll());
    }

    @DeleteMapping("/es")
    public Result deleteFromEs(@RequestParam Long productId) {
        return Result.success(productService.deleteFromEs(productId));
    }

    @DeleteMapping("/es/batch")
    public Result deleteBathFromEs(@RequestParam Set<Long> productIds) {
        return Result.success(productService.deleteBatchFromEs(productIds));
    }

    @GetMapping("/es/search")
    public Result<Page<ProductPO>> search(@RequestParam String query, @PageableDefault Pageable pageable) {
        return Result.success(productService.search(query, pageable));
    }

    @PostMapping("/es/{productId}")
    public Result<ProductPO> create(@PathVariable Long productId) {
        return Result.success(productService.create(productId));
    }


}

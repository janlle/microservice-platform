package com.leone.microservice.search.repository;

import com.leone.microservice.search.entity.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-29
 **/
public interface PhoneRepository extends ElasticsearchRepository<Phone, Integer> {

    /**
     * @param phone
     * @param title
     * @param name
     * @param page
     * @return
     */
    Page<Phone> findByPhoneOrTitleOrName(String phone, String title, String name, Pageable page);

}

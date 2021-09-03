package com.study.ecommerce.repository;

import com.study.ecommerce.entity.EcommerceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcommerceUserRepository extends JpaRepository<EcommerceUser, Long> {

    EcommerceUser findFirstByUsernameAndDeletedIsFalse(String userName);
}

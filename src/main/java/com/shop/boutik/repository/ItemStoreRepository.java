package com.shop.boutik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.boutik.model.ItemStore;

@Repository
public interface ItemStoreRepository extends JpaRepository<ItemStore, Long> {

}

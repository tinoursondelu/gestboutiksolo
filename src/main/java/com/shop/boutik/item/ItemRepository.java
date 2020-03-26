package com.shop.boutik.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.boutik.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}

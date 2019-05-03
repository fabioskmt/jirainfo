package com.everis.jirainfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.jirainfo.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String>{

}

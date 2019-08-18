package com.agazibaric.dao;

import com.agazibaric.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface AuctionDao extends CrudRepository<Item, Long> {



}

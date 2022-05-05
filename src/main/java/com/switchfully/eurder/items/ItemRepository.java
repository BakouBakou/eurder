package com.switchfully.eurder.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

}

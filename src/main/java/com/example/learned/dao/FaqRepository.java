package com.example.learned.dao;

import com.example.learned.entity.CategoryEntity;
import com.example.learned.entity.FaqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<FaqEntity,Long> {
}

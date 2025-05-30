package com.transacationlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transacationlearn.model.Learn;

public interface LearnRepository extends JpaRepository<Learn, Long> {
}
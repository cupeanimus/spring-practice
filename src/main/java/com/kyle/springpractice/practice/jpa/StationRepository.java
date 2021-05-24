package com.kyle.springpractice.practice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StationRepository extends JpaRepository<Station, Long> {
    Station findByName(String name);
}

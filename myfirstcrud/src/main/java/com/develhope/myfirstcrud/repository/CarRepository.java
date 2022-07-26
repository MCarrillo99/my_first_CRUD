package com.develhope.myfirstcrud.repository;

import com.develhope.myfirstcrud.entities.Car;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}

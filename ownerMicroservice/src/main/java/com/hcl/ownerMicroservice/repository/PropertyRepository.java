package com.hcl.ownerMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ownerMicroservice.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

}

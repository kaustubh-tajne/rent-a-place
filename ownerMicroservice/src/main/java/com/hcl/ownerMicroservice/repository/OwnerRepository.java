package com.hcl.ownerMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ownerMicroservice.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}

package com.rohit.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rohit.ems.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    
}

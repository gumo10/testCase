package com.genesis.testcase.repository;

import com.genesis.testcase.beans.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

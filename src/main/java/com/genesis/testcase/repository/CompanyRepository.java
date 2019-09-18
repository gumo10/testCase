package com.genesis.testcase.repository;

import com.genesis.testcase.beans.Address;
import com.genesis.testcase.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {}

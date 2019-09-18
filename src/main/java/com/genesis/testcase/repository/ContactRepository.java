package com.genesis.testcase.repository;

import com.genesis.testcase.beans.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {}

package com.example.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.firstproject.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}

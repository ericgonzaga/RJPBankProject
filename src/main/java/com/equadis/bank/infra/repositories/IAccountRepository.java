package com.equadis.bank.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.equadis.bank.infra.entities.AccountEntity;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query("SELECT a FROM AccountEntity a WHERE a.customerId = ?1")
    public List<AccountEntity> findByCustomerId(Long customerId);
}

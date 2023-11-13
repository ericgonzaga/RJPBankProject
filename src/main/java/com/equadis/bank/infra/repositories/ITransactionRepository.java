package com.equadis.bank.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.equadis.bank.infra.entities.TransactionEntity;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT t FROM TransactionEntity t WHERE t.accountId = ?1")
    public List<TransactionEntity> findByAccountId(Long accountId);
}

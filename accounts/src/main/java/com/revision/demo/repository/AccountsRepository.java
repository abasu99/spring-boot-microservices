package com.revision.demo.repository;

import com.revision.demo.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    public Accounts findByCustomerId(Long customerId);

    @Transactional
    @Modifying
    public void deleteByCustomerId(Long customerId);
}

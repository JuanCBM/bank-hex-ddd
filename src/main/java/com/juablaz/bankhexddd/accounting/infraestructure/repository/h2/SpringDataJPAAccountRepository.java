package com.juablaz.bankhexddd.accounting.infraestructure.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPAAccountRepository extends JpaRepository<AccountEntity, String> {

}

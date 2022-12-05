package com.juablaz.bankhexddd.infraestructure.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPAAccountRepository extends JpaRepository<AccountEntity, Long> {

}

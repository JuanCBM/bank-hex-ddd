package com.juablaz.bankhexddd.analytics.infraestructure.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPAAnalyticAccountRepository extends
    JpaRepository<AnalyticAccountEntity, String> {

}

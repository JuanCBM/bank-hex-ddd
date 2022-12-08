package com.juablaz.bankhexddd.analytics.infraestructure.repository.h2;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataJPAAnalyticAccountRepository extends
    JpaRepository<AnalyticAccountEntity, String> {

  @Query("SELECT a FROM AnalyticAccountEntity a WHERE a.currency = :currency")
  List<AnalyticAccountEntity> findAccountsPerCurrency(@Param("currency") String currency);
}

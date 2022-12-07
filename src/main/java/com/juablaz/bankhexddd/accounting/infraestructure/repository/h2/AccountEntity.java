package com.juablaz.bankhexddd.accounting.infraestructure.repository.h2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "account")
@Data
@Entity
@NoArgsConstructor
public class AccountEntity {

  @Id
  public String id;

  public String name;

  @Column(name = "_value")
  public Float value;

  public String currency;

}

package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "userEntity")
public class UserEntity implements Serializable {
  @Id
  private Long id;
  @Column
  private String name;
  @Column
  private String email;
  @Column
  private Integer age;
}

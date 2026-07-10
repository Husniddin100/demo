package com.example.demo.entity;

import com.example.demo.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
  @Column(unique = true, nullable = false)
  private String email;
  @Column
  private Integer age;

  @Enumerated(EnumType.STRING)
  private UserRole role;

}

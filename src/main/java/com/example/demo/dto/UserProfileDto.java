package com.example.demo.dto;

import com.example.demo.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserProfileDto implements Serializable {
  private Long id;
  private String name;
  private String email;
  private Integer age;
  private UserRole role;

  public UserProfileDto() {
  }


}


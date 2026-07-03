package com.example.demo.controller;

import com.example.demo.dto.UserProfileDto;
import com.example.demo.service.UserProfileCacheService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserProfileCacheService cacheService;

  public UserController(UserProfileCacheService cacheService) {
    this.cacheService = cacheService;
  }

  @PostMapping
  public String saveProfile(@RequestBody UserProfileDto profile) {
    cacheService.cacheUserProfile(profile);
    return "Profil Redis-ga muvaffaqiyatli yozildi!";
  }

  @GetMapping("/{id}")
  public UserProfileDto getProfile(@PathVariable Long id) {
    return cacheService.getUserProfile(id);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteProfile(@PathVariable Long id) {
    cacheService.deleteUserProfile(id);
  }

  @PutMapping("/update/{id}")
  public void updateUsername(@PathVariable Long id, @RequestBody UserProfileDto profile) {
    cacheService.updateUserName(id, profile.getName());
  }
}
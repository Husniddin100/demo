package com.example.demo.service;

import com.example.demo.dto.UserProfileDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UserProfileCacheService {

  private final UserRepository userRepository;
  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;

  public void cacheUserProfile(UserProfileDto dto) {

    UserEntity entity = new UserEntity();
    entity.setId(dto.getId());
    entity.setName(dto.getName());
    entity.setEmail(dto.getEmail());
    entity.setAge(dto.getAge());

    UserEntity savedEntity = userRepository.save(entity);


    String redisKey = "user:profile:" + dto.getId();
    Map<String, Object> profileMap = objectMapper.convertValue(dto, Map.class);

    redisTemplate.opsForHash().putAll(redisKey, profileMap);
    redisTemplate.expire(redisKey, 1, TimeUnit.HOURS);
  }

  public UserProfileDto getUserProfile(Long userId) {
    String redisKey = "user:profile:" + userId;

    Map<Object, Object> entries = redisTemplate.opsForHash().entries(redisKey);

    if (!entries.isEmpty()) {
      return objectMapper.convertValue(entries, UserProfileDto.class);
    }

    UserEntity entity = userRepository.findById(userId).orElse(null);

    if (entity == null) {
      return null;
    }

    UserProfileDto dto = new UserProfileDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setEmail(entity.getEmail());
    dto.setAge(entity.getAge());

    Map<String, Object> profileMap = objectMapper.convertValue(dto, Map.class);
    redisTemplate.opsForHash().putAll(redisKey, profileMap);
    redisTemplate.expire(redisKey, 1, java.util.concurrent.TimeUnit.HOURS);

    return dto;
  }


  public void updateUserName(Long userId, String newName) {
    String redisKey = "user:profile:" + userId;

    redisTemplate.opsForHash().put(redisKey, "name", newName);
  }

  public void deleteUserProfile(Long id) {
    String redisKey = "user:profile:" + id;
    redisTemplate.delete(redisKey);
  }
}
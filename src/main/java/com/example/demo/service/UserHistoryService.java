package com.example.demo.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserHistoryService {

  private final RedisTemplate<String, Object> redisTemplate;

  public UserHistoryService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void addProductToHistory(Long userId, String productName) {
    String redisKey = "user:history:" + userId;

    redisTemplate.opsForList().leftPush(redisKey, productName);

    redisTemplate.opsForList().trim(redisKey, 0, 4);

    redisTemplate.expire(redisKey, 24, TimeUnit.HOURS);
  }

  public List<Object> getUserHistory(Long userId) {
    String redisKey = "user:history:" + userId;

    return redisTemplate.opsForList().range(redisKey, 0, -1);
  }
}
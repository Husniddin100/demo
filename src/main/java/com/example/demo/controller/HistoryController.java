package com.example.demo.controller;

import com.example.demo.service.UserHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

  private final UserHistoryService historyService;

  public HistoryController(UserHistoryService historyService) {
    this.historyService = historyService;
  }


  @PostMapping("/{userId}")
  public String viewProduct(@PathVariable Long userId, @RequestParam String productName) {
    historyService.addProductToHistory(userId, productName);
    return productName + " tarixga qo'shildi!";
  }


  @GetMapping("/{userId}")
  public List<Object> getHistory(@PathVariable Long userId) {
    return historyService.getUserHistory(userId);
  }
}
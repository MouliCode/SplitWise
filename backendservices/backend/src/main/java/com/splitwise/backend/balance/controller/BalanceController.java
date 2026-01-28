package com.splitwise.backend.balance.controller;

import com.splitwise.backend.balance.dto.BalanceResponse;
import com.splitwise.backend.balance.service.BalanceService;
import com.splitwise.backend.common.dto.response.ApiResponse;
import com.splitwise.backend.common.security.SecurityContextUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/balances")
public class BalanceController {
   
   private final BalanceService balanceService;
   
   public BalanceController(BalanceService balanceService){
	  this.balanceService = balanceService;
   }
   
   @GetMapping("/me")
   public ApiResponse<List<BalanceResponse>> myBalances(){
	  UUID userId = SecurityContextUtil.getCurrentUserId ();
	  return ApiResponse.success (balanceService.getUserBalances (userId));
   }
}

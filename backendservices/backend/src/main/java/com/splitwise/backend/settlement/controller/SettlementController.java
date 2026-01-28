package com.splitwise.backend.settlement.controller;

import com.splitwise.backend.common.dto.response.ApiResponse;
import com.splitwise.backend.settlement.dto.SettlementRequest;
import com.splitwise.backend.settlement.service.SettlementService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settlements")
public class SettlementController {
   
   private final SettlementService service;
   
   public SettlementController (SettlementService service) { this.service = service; }
   
   @PostMapping
   public ApiResponse<Void> settle(@RequestBody @Valid SettlementRequest request){
	  service.settle (
			  request.fromUserId (),
			  request.toUserId (),
			  request.amount ()
	  );
	  
	  return ApiResponse.success (null);
   }
   
}

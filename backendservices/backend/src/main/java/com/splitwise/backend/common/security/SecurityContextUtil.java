package com.splitwise.backend.common.security;

import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class SecurityContextUtil {
   
   private SecurityContextUtil () { }
   
   public static UUID getCurrentUserId () {
	  
	  Authentication authentication =
			  SecurityContextHolder.getContext ().getAuthentication ();
	  
	  if (authentication == null || authentication.getPrincipal () == null) {
		 throw new BusinessException (StandardResponseCode.UNAUTHORIZED,
				 StandardResponseCode.UNAUTHORIZED.getMessage ());
	  }
	  
	  return (UUID) authentication.getPrincipal ();
   }
   
}

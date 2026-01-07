//package com.splitwise.backend.common.security;
//
//import com.splitwise.backend.domain.repository.UserRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class UserContextFilter extends OncePerRequestFilter {
//
//	private UserRepository repo;
//
//	@Override
//	protected void doFilterInternal (HttpServletRequest request,
//	                                 HttpServletResponse response,
//	                                 FilterChain filterChain) throws ServletException, IOException {
//
//		Authentication auth = SecurityContextHolder.getContext ().getAuthentication ();
//		if (auth != null && auth.isAuthenticated ()
//				    && !"anonymousUser".equals (auth.getPrincipal ())) {
//
//			String identifier = auth.getName ();
//
//			repo.findByEmail (identifier)
//					.or (() ->
//							     repo.findByPhone (identifier)).
//					ifPresent (user ->
//							           UserContext.set (
//									           user.getId (),
//									           user.getName (),
//									           user.getPhone (),
//									           user.getEmail ()
//							           )
//					);
//
//			try {
//				filterChain.doFilter (request, response);
//			} finally {
//				UserContext.clear ();
//			}
//		}
//
//	}
//}

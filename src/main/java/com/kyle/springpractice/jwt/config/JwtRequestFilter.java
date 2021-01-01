package com.kyle.springpractice.jwt.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyle.springpractice.exception.dto.ErrorResponse;
import com.kyle.springpractice.jwt.service.BpUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	private final BpUserDetailsService bpUserDetailsService;
	private final JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain chain)
			throws ServletException, IOException {


		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		String channel = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				UsernamePasswordAuthenticationToken athentications = jwtTokenUtil.getAthentications(jwtToken);
				channel = jwtTokenUtil.getChannel(jwtToken);
				SecurityContextHolder.getContext().setAuthentication(athentications);
			} catch (IllegalArgumentException e) {
				log.error("Unable to get JWT Token");
				ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),"Unable to get JWT Token");
				httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				PrintWriter writer = httpServletResponse.getWriter();
				writer.write(convertObjectToJson(errorResponse));
				return;

			} catch (ExpiredJwtException e) {
				log.error("JWT Token has expired");
				ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),"JWT Token has expired");
				httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				PrintWriter writer = httpServletResponse.getWriter();
				writer.write(convertObjectToJson(errorResponse));
				return;
			}

		} else {

			SecurityContextHolder.getContext().setAuthentication(null);

			String  uri = request.getRequestURI();
			if (!uri.contains("/member/") && !uri.contains("health-check")){
				log.warn("JWT Token does not begin with Bearer String. request uri {}",uri);
			}
		}


		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.bpUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		chain.doFilter(request, httpServletResponse);
	}

	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
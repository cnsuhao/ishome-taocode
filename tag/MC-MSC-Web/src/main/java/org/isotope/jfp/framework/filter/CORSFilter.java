package org.isotope.jfp.framework.filter;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * CORS(跨域)过滤器.
 */
public class CORSFilter extends OncePerRequestFilter {
//	private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Content-Type", "application/json");
		filterChain.doFilter(request, response);
		
	}

}
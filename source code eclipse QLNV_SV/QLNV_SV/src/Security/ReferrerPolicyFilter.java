package Security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ReferrerPolicyFilter implements Filter{
	@Override
	public void init(FilterConfig arg0) { }

	@Override
	public void destroy() { }
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	                     FilterChain chain) throws IOException, ServletException {

	    HttpServletResponse httpServletResponse = ((HttpServletResponse) response);
	    httpServletResponse.addHeader("Referrer-Policy", "strict-origin-when-cross-origin");
	    chain.doFilter(request, response);
	}

	
}

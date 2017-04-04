package ie.gmit.sw.Security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Christopher Weir - G00309429
 * 
 * Filter for Client Authentication.
 *
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		this.context.log("Requested Resource::"+uri);

		HttpSession session = req.getSession();

		//If no authentication code is detected and the client is not on the Login page
		if(session.getAttribute("code") == null && !uri.contains("Login")){
			//If the uri contains the following
			if(uri.endsWith("css") || uri.endsWith("js") || uri.contains("Login") || uri.contains("Register") || uri.contains("AccountRecovery") || uri.contains("ErrorHandler")){
				chain.doFilter(request, response);
			}else{
				this.context.log("Unauthorized access request");
				res.sendRedirect("Login");
			}
		}else{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

}

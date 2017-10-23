package ru.innopolis.refrigerator.servlet.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.refrigerator.core.db.jdbc.exception.SessionDAOException;
import ru.innopolis.refrigerator.service.AuthorizationService;
import ru.innopolis.refrigerator.service.AuthorizationServiceImpl;
import ru.innopolis.refrigerator.service.exception.AuthorizationServiceImplException;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

	private static AuthorizationService as = new AuthorizationServiceImpl();
	private static final Logger logger = LogManager.getLogger(LoginFilter.class.getName());
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		filterConfig = filterConfig;
		//filterConfig.getServletContext().getRequestDispatcher("/path/to/static-file.jsp").forward(request, response);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getServletPath().contains("/auth") || req.getServletPath().contains("/reg") ||
				req.getServletPath().contains("/auth.") || req.getServletPath().contains("/reg.") ||
				req.getServletPath().contains("/css") || req.getServletPath().contains("/js") ||
				req.getServletPath().contains("/image"))
		{
			chain.doFilter(request, response);
		} else {
			Cookie[] cookies = req.getCookies();
			String sid = as.getCookie(AuthorizationServiceImpl.COOKIE_SID, cookies);
			Long uid = Long.valueOf(as.getCookie(AuthorizationServiceImpl.COOKIE_UID, cookies));

			if (sid != null && uid != null && sid.length() != 0 && uid != 0) {
				try {
					if (as.authByCookies(sid, uid)) {
						chain.doFilter(req, response);
					} else {
						req.getServletContext().getRequestDispatcher("/auth.jsp").forward(req, (HttpServletResponse) response);
					}
				}
				catch (AuthorizationServiceImplException e) {
					logger.error(e.getMessage(), e.toString());
					((HttpServletResponse) response).sendRedirect("/web-refrigerator/auth");
				}
				catch (SessionDAOException e) {
					logger.error(e.getMessage(), e.toString());
					((HttpServletResponse) response).sendRedirect("/web-refrigerator/auth");
				}
			} else {
				((HttpServletResponse) response).sendRedirect("/web-refrigerator/auth");
			}
		}
	}

	@Override
	public void destroy() {

	}
}

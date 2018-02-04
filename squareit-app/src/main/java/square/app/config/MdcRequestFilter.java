package square.app.config;

import square.api.HeadersDefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MdcRequestFilter extends OncePerRequestFilter {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws ServletException, IOException {
    String breadCrumbId = httpServletRequest.getHeader(HeadersDefinition.BREAD_CRUMB_ID);
    logger.trace("Setting MDC-->{} = {}", HeadersDefinition.BREAD_CRUMB_ID, breadCrumbId);
    MDC.put(HeadersDefinition.BREAD_CRUMB_ID, breadCrumbId);

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
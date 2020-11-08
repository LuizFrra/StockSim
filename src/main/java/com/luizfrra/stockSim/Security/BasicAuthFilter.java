package com.luizfrra.stockSim.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.BasicAuthRequestAuthenticator;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.spi.AuthOutcome;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BasicAuthFilter extends OncePerRequestFilter {

    private ApplicationContext applicationContext;

    private String basicEndPoint;

    public BasicAuthFilter(ApplicationContext applicationContext, String basicEndPoint) {
        this.applicationContext = applicationContext;
        this.basicEndPoint = basicEndPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        HttpFacade facade =  new SimpleHttpFacade(request, response);
        AdapterDeploymentContext adapterDeploymentContext = applicationContext.getBean(AdapterDeploymentContext.class);
        KeycloakDeployment deployment = adapterDeploymentContext.resolveDeployment(facade);
        BasicAuthRequestAuthenticator basicAuth = new BasicAuthRequestAuthenticator(deployment);
        AuthOutcome outcome = basicAuth.authenticate(facade);

        if(outcome == AuthOutcome.AUTHENTICATED) {
            String access_token = basicAuth.getTokenString();
            long exp = basicAuth.getToken().getExp();
            String token_type = "Bearer ";

            TokenCreationResponse tokenCreationResponse = new TokenCreationResponse(access_token, exp, token_type);

            response.setStatus(200);
            response.setContentType("application/json");
            String jsonTokenResponse = new ObjectMapper().writeValueAsString(tokenCreationResponse);
            response.getWriter().write(jsonTokenResponse);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getRequestURI().equals(basicEndPoint);
    }
}

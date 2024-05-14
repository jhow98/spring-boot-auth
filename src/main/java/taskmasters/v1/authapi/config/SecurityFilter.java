package taskmasters.v1.authapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import taskmasters.v1.authapi.models.Users;
import taskmasters.v1.authapi.repositories.UserRepository;
import taskmasters.v1.authapi.services.implementation.AuthService;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractTokenHeader(request);

        if (token != null) {
            String login = authService.validateToken(token);
            Users users = userRepository.findByLogin(login);

            if (users == null) {
                //throw  new UnauthorizedException("Unauthorizad");
            }

            var authentication = new UsernamePasswordAuthenticationToken(users, null, users.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    public String extractTokenHeader(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        }

        if (!authHeader.split(" ")[0].equals("Bearer")) {
            return  null;
        }

        return authHeader.split(" ")[1];
    }
}
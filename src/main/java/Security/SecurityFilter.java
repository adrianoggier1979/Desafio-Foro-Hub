package Security;

import Repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.token.TokenService;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import Security.TokenService;

import java.io.IOException;
import java.security.Security;

public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "").trim();
            var subject = tokenService.getSubject(token);
            if (subject != null) {
                var usuario = usuarioRepository.findByEmail(subject);
                if (usuario != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }


//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//        throws ServletException, IOException {
//        var authHeader = request.getHeader("Authorization");
//        if (authHeader != null) {
//
//            var token = authHeader.replace("Bearer", "");
//            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
//            if (subject != null) {
//                var usuario = usuarioRepository.findByEmail(subject);
//                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//
//        }
//        filterChain.doFilter(request, response);
//    }
//

        }
    }
}

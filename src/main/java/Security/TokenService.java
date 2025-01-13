package Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import Security.TokenService;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.AlgorithmConstraints;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


//import static org.springframework.security.config.Elements.JWT;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generateToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro alura")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .sign(algorithm);


        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }

    }

    public String getSubject(String token){
        if (token == null){
            throw new RuntimeException();
        }
        //DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("foro alura")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Instant generarFecheExpiration(Integer numeroHrs){
        return LocalDateTime.now().plusHours(numeroHrs).toInstant(ZoneOffset.of("-06:00"));
    }
}

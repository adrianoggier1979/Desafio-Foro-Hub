package dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import modelo.Tipo;

public record DatosRegistroUsuario(


        @NotBlank
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        String contrasena,
        @NotNull
        Tipo tipo) {

}

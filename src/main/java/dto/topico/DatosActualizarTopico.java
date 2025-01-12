package dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import modelo.Estado;

public record DatosActualizarTopico(

        @NotNull
        Long id,

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotNull
        Estado estado,

        @NotNull
        Long autorId,

        @NotNull
        Long cursoId
) {
}

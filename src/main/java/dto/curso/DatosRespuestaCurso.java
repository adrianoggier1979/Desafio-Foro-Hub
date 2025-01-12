package dto.curso;

import modelo.Curso;

public record DatosRespuestaCurso(String nombre, String categoria) {

    public DatosRespuestaCurso(Curso curso){
        this(curso.getNome(), curso.getCategoria());
    }
}

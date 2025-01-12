package dto.topico;

import dto.curso.DatosRespuestaCurso;
import dto.usuario.DatosRegistroUsuario;
import dto.usuario.DatosRespuestaUsuario;
import modelo.Topico;

public record DatosRespuestaTopicoId(Long id, String titulo, String mensaje, String fechaCreacion,
                                     String estado, DatosRespuestaUsuario autor,  DatosRespuestaCurso curso) {

    public DatosRespuestaTopicoId(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getEstado().toString(),
                new DatosRespuestaUsuario(topico.getAutor()),
                new DatosRespuestaCurso(topico.getCurso()));
    }


}

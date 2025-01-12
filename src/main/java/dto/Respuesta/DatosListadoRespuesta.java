package dto.Respuesta;

import modelo.Respuesta;

public record DatosListadoRespuesta(Long id, String mensaje, String topico, String autor) {

    public DatosListadoRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo(),respuesta.getAutor().getNombre());
    }
}

package dto.topico;

import modelo.Topico;

public record DatosRespuestaTopico(String titulo, String mensaje, String autor, String curso) {

    public DatosRespuestaTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(), topico.getCurso().getNome());
    }
}

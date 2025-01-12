package modelo;

import dto.Respuesta.DatosRegistroRespuesta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "respuestas")
@Entity(name = "respuesta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;


    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private Usuario autor;

    private Boolean solucion = false;

    public Respuesta(DatosRegistroRespuesta datos, Topico topico, Usuario autor){
        this.mensaje = datos.mensaje();
        this.topico = topico;
        this.autor = autor;
        this.solucion = datos.solucion();

        if (datos.solucion()) {
            this.topico.setEstado(Estado.RESUELTO);
        } else {
            this.topico.setEstado(Estado.NO_RESUELTO);
        }

    }
}

package proyectoweb.sistemadecitas.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectoweb.sistemadecitas.model.Reserva;
import proyectoweb.sistemadecitas.model.Servicio;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

    // Buscar una reserva para un servicio y una fecha y hora específicas
    Reserva findByServicioAndFechaHora(Servicio servicio, LocalDateTime fechaHora);

    // Buscar si existe alguna reserva en el rango de tiempo (esto es útil si deseas verificar el intervalo)
    Reserva findByServicioAndFechaHoraBetween(Servicio servicio, LocalDateTime start, LocalDateTime end);
    
}

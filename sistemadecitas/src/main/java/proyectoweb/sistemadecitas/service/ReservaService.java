package proyectoweb.sistemadecitas.service;

import proyectoweb.sistemadecitas.model.Reserva;
import proyectoweb.sistemadecitas.model.Servicio;
import proyectoweb.sistemadecitas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // Verificar si el servicio está disponible para la fecha y hora seleccionada
    public boolean verificarDisponibilidad(Servicio servicio, LocalDateTime fechaHora) {
        // Verificar si ya hay una reserva en ese horario y servicio
        Reserva reservaExistente = reservaRepository.findByServicioAndFechaHora(servicio, fechaHora);
        return reservaExistente == null;  // Si es null, significa que el horario está disponible
    }

    // Realizar la reserva
    public Reserva realizarReserva(Servicio servicio, LocalDateTime fechaHora) {
        if (verificarDisponibilidad(servicio, fechaHora)) {
            Reserva nuevaReserva = new Reserva();
            nuevaReserva.setServicio(servicio);
            nuevaReserva.setFechaHora(fechaHora);
            return reservaRepository.save(nuevaReserva);  // Guardamos la nueva reserva en la base de datos
        } else {
            throw new RuntimeException("El servicio ya está reservado para esa fecha y hora.");
        }
    }
}

package br.com.sandes.repositories;

import br.com.sandes.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CambioReopsitory extends JpaRepository<Cambio, Long> {

    Cambio findByFromAndTo(String from, String to);
}

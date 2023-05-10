package co.edu.uniquindio.proyecto.repo;


import co.edu.uniquindio.proyecto.dto.PagoQueryDTO;
import co.edu.uniquindio.proyecto.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepo extends JpaRepository<Pago, String> {
    @Query("select new co.edu.uniquindio.proyecto.dto.PagoQueryDTO(p.codigo, q.valorPago, p.codigoCliente, p.fechaPago,q.tipoPago) from Pago p join p.codigoProductos l where p.codigoCliente = :codigoCliente")
    List<PagoQueryDTO> findByCodigoCliente(String codigoCliente);


}

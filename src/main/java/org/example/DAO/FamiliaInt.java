package org.example.DAO;

import org.example.entities.Familia;
import java.util.List;

public interface FamiliaInt {

    /**
     * @return todas las familias de la base de datos.
     */
    List<Familia> findAll();

    /**
     * Busca una familia por su ID.
     * @param id Identificador único de la familia.
     * @return Familia encontrada o null si no existe.
     */
    Familia findById(Long id);

    /**
     * Busca familias por ciudad.
     * @param ciudad Nombre de la ciudad.
     * @return Lista de familias que viven en la ciudad especificada.
     */
    List<Familia> findByCiudad(String ciudad);

    /**
     * Crea una nueva familia en la base de datos.
     * @param familia Objeto familia a crear.
     * @return Familia creada.
     */
    Familia create(Familia familia);

    /**
     * Actualiza los datos de una familia existente.
     * @param familia Objeto familia con los datos actualizados.
     * @return Familia actualizada.
     */
    Familia update(Familia familia);

    /**
     * Elimina una familia por su ID.
     * @param id Identificador único de la familia.
     * @return True si se eliminó correctamente, false en caso contrario.
     */
    boolean deleteById(Long id);
}

package org.example.DAO;

import org.example.entities.Animales;
import org.hibernate.HibernateException;

import java.util.List;

public interface AnimalesInt {

    /**
     * @return Lista con todos los animales en la base de datos.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    List<Animales> findAll() throws HibernateException;

    /**
     * Busca animales por especie.
     * @param especie Especie a buscar.
     * @return Lista de animales que coincidan con la especie.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    List<Animales> findAllByEspecie(String especie) throws HibernateException;

    /**
     * Busca animales por edad.
     * @param edad Edad de los animales a buscar.
     * @return Lista de animales que coincidan con la edad.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    List<Animales> findAllByEdad(int edad) throws HibernateException;

    /**
     * Busca animales por descripción.
     * @param descripcion Descripción a buscar.
     * @return Lista de animales que coincidan con la descripción.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    List<Animales> findAllByDescripcion(String descripcion) throws HibernateException;

    /**
     * Inserta un nuevo animal en la base de datos.
     * @param animal Objeto del animal a insertar.
     * @return El animal insertado.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    Animales create(Animales animal) throws HibernateException;

    /**
     * Actualiza un animal existente en la base de datos.
     * @param animal Objeto del animal con los datos actualizados.
     * @return El animal actualizado.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    Animales update(Animales animal) throws HibernateException;

    /**
     * Elimina un animal de la base de datos por su ID.
     * @param id ID del animal a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    boolean deleteById(Long id) throws HibernateException;
}

package org.example.DAO;

import org.example.entities.Animales;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Clase que implementa la interfaz AnimalesInt para gestionar operaciones CRUD y consultas
 * relacionadas con la entidad Animales utilizando Hibernate.
 */
public class AnimalesImpl implements AnimalesInt {

    private Session session;

    /**
     * Constructor de la clase AnimalesImpl.
     *
     * @param session La sesión de Hibernate que se utilizará para realizar las operaciones.
     */
    public AnimalesImpl(Session session) {
        this.session = session;
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Animales.class)
                .buildSessionFactory();
    }

    /**
     * Obtiene todos los registros de la tabla Animales.
     *
     * @return Una lista con todos los animales en la base de datos.
     * @throws HibernateException Si ocurre un error al realizar la consulta.
     */
    @Override
    public List<Animales> findAll() throws HibernateException {
        try {
            return session.createQuery("FROM Animales", Animales.class).getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar animales.", e);
        }
    }

    /**
     * Busca todos los animales según la especie especificada.
     *
     * @param especie La especie de los animales a buscar.
     * @return Una lista con los animales que coincidan con la especie.
     * @throws HibernateException Si ocurre un error al realizar la consulta.
     */
    @Override
    public List<Animales> findAllByEspecie(String especie) throws HibernateException {
        try {
            return session.createQuery("FROM Animales WHERE especie = :especie", Animales.class)
                    .setParameter("especie", especie)
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar los animales por especie.", e);
        }
    }

    /**
     * Busca todos los animales según la edad especificada.
     *
     * @param edad La edad de los animales a buscar.
     * @return Una lista con los animales que coincidan con la edad.
     * @throws HibernateException Si ocurre un error al realizar la consulta.
     */
    @Override
    public List<Animales> findAllByEdad(int edad) throws HibernateException {
        try {
            return session.createQuery("FROM Animales WHERE edad = :edad", Animales.class)
                    .setParameter("edad", edad)
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar los animales por edad.", e);
        }
    }

    /**
     * Busca todos los animales cuya descripción coincida con el texto dado.
     *
     * @param descripcion Una parte de la descripción a buscar.
     * @return Una lista con los animales cuya descripción coincida con el texto.
     * @throws HibernateException Si ocurre un error al realizar la consulta.
     */
    @Override
    public List<Animales> findAllByDescripcion(String descripcion) throws HibernateException {
        try {
            return session.createQuery("FROM Animales WHERE descripcion LIKE :descripcion", Animales.class)
                    .setParameter("descripcion", "%" + descripcion + "%")
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar los animales por descripción.", e);
        }
    }

    /**
     * Crea un nuevo registro de animal en la base de datos.
     *
     * @param animal El objeto Animales a insertar.
     * @return El objeto Animales creado.
     * @throws HibernateException Si ocurre un error al realizar la operación.
     */
    @Override
    public Animales create(Animales animal) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
            return animal;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al crear el animal.", e);
        }
    }

    /**
     * Actualiza un registro existente de animal en la base de datos.
     *
     * @param animal El objeto Animales con los datos actualizados.
     * @return El objeto Animales actualizado.
     * @throws HibernateException Si ocurre un error al realizar la operación.
     */
    @Override
    public Animales update(Animales animal) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(animal);
            transaction.commit();
            return animal;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error actualizando el animal.", e);
        }
    }

    /**
     * Elimina un registro de animal de la base de datos por su ID.
     *
     * @param id El ID del animal a eliminar.
     * @return {@code true} si el animal fue eliminado, {@code false} si no se encontró.
     * @throws HibernateException Si ocurre un error al realizar la operación.
     */
    @Override
    public boolean deleteById(Long id) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Animales animal = session.get(Animales.class, id);
            if (animal != null) {
                session.delete(animal);
                transaction.commit();
                return true;
            }
            return false;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al borrar el animal.", e);
        }
    }
}

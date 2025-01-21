package org.example.DAO;

import org.example.entities.Animales;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import java.util.List;

public class AnimalesImpl implements AnimalesInt {

    /**
     * Devuelve todos los animales de la base de datos.
     */
    @Override
    public List<Animales> findAll() throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Animales> animales;

        try {
            animales = session.createQuery("from Animales", Animales.class).list();
        } catch (HibernateException e) {
            throw new HibernateException("Error al obtener todos los animales", e);
        } finally {
            session.close();
        }

        return animales;
    }

    /**
     * Busca animales por especie.
     */
    @Override
    public List<Animales> findAllByEspecie(String especie) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Animales> animales;

        try {
            animales = session.createQuery("from Animales where especie = :especie", Animales.class)
                    .setParameter("especie", especie)
                    .list();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar animales por especie", e);
        } finally {
            session.close();
        }

        return animales;
    }

    /**
     * Busca animales por edad.
     */
    @Override
    public List<Animales> findAllByEdad(int edad) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Animales> animales;

        try {
            animales = session.createQuery("from Animales where edad = :edad", Animales.class)
                    .setParameter("edad", edad)
                    .list();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar animales por edad", e);
        } finally {
            session.close();
        }

        return animales;
    }

    /**
     * Busca animales por descripción.
     *
     * @param descripcion Descripción a buscar.
     * @return Lista de animales que coincidan con la descripción.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public List<Animales> findAllByDescripcion(String descripcion) throws HibernateException {
        return List.of();
    }

    /**
     * Crea un nuevo animal en la base de datos.
     */
    @Override
    public Animales create(Animales animal) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(animal);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("Error al insertar un animal", e);
        } finally {
            session.close();
        }

        return animal;
    }

    /**
     * Actualiza un animal existente.
     */
    @Override
    public Animales update(Animales animal) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(animal);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("Error al actualizar un animal", e);
        } finally {
            session.close();
        }

        return animal;
    }

    /**
     * Elimina un animal por su ID.
     */
    @Override
    public boolean deleteById(Long id) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Animales animal = session.find(Animales.class, id);
            if (animal != null) {
                session.remove(animal);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("Error al eliminar un animal", e);
        } finally {
            session.close();
        }
    }
}


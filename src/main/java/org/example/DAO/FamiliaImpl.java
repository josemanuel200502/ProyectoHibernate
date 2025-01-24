package org.example.DAO;

import org.example.entities.Familia;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class FamiliaImpl implements FamiliaInt {

    private Session session;

    // Constructor que recibe la sesión
    public FamiliaImpl(Session session) {
        this.session = session;
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Familia.class).buildSessionFactory();

    }
    /**
     * Devuelve todas las familias de la base de datos.
     */
    @Override
    public List<Familia> findAll() {
        Session session = HibernateUtil.getSession();
        List<Familia> familias;

        try {
            familias = session.createQuery("from Familia", Familia.class).list();
        } catch (HibernateException e) {
            throw new HibernateException("Error al obtener todas las familias", e);
        } finally {
            session.close();
        }

        return familias;
    }

    /**
     * Busca una familia por su ID.
     */
    @Override
    public Familia findById(Long id) {
        Session session = HibernateUtil.getSession(); // Usar getSession()
        Familia familia;

        try {
            familia = session.find(Familia.class, id);
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar una familia por ID", e);
        } finally {
            session.close();
        }

        return familia;
    }


    /**
     * Busca familias por ciudad.
     */
    @Override
    public List<Familia> findByCiudad(String ciudad) {
        Session session = HibernateUtil.getSession();
        List<Familia> familias;

        try {
            familias = session.createQuery("from Familia where ciudad = :ciudad", Familia.class)
                    .setParameter("ciudad", ciudad)
                    .list();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar familias por ciudad", e);
        } finally {
            session.close();
        }

        return familias;
    }

    /**
     * Crea una nueva familia.
     */
    @Override
    public Familia create(Familia familia) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(familia);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("Error al crear una familia", e);
        } finally {
            session.close();
        }

        return familia;
    }

    /**
     * Actualiza una familia existente.
     */
    @Override
    public Familia update(Familia familia) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(familia);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("Error al actualizar una familia", e);
        } finally {
            session.close();
        }

        return familia;
    }

    /**
     * Elimina una familia por su ID.
     */
    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Familia familia = session.find(Familia.class, id);
            if (familia != null) {
                session.remove(familia);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("Error al eliminar una familia", e);
        } finally {
            session.close();
        }
    }
}

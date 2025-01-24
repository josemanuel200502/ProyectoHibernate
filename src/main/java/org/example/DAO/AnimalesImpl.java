package org.example.DAO;

import org.example.entities.Animales;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AnimalesImpl implements AnimalesInt {

    private Session session;

    public AnimalesImpl(Session session) {
        this.session = session;
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Animales.class).buildSessionFactory();

    }



    @Override
    public List<Animales> findAll() throws HibernateException {
        try {
            return session.createQuery("FROM Animales", Animales.class).getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar animales.", e);
        }
    }

    @Override
    public List<Animales> findAllByEspecie(String especie) throws HibernateException {
        try {
            return session.createQuery("FROM Animales WHERE especie = :especie", Animales.class)
                    .setParameter("especie", especie)
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar los animales por especie .", e);
        }
    }

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

    @Override
    public List<Animales> findAllByDescripcion(String descripcion) throws HibernateException {
        try {
            return session.createQuery("FROM Animales WHERE descripcion LIKE :descripcion", Animales.class)
                    .setParameter("descripcion", "%" + descripcion + "%")
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar los animales por descripcion .", e);
        }
    }

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
            throw new HibernateException("Error al borrar  animal .", e);
        }
    }
}

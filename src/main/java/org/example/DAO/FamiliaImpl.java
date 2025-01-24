package org.example.DAO;

import org.example.entities.Familia;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Implementación de la interfaz {@link FamiliaInt} para gestionar las operaciones de la entidad {@link Familia}.
 * Proporciona métodos para realizar operaciones CRUD y búsquedas específicas.
 */
public class FamiliaImpl implements FamiliaInt {

    private Session session;

    /**
     * Constructor que inicializa la sesión para realizar operaciones con Hibernate.
     *
     * @param session Sesión de Hibernate proporcionada externamente.
     */
    public FamiliaImpl(Session session) {
        this.session = session;
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Familia.class).buildSessionFactory();
    }

    /**
     * Obtiene todas las familias registradas en la base de datos.
     *
     * @return Lista de todas las familias.
     * @throws HibernateException Si ocurre un error al realizar la consulta.
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
     * Busca una familia en la base de datos por su ID.
     *
     * @param id Identificador único de la familia.
     * @return La familia encontrada o {@code null} si no se encuentra.
     * @throws HibernateException Si ocurre un error al realizar la consulta.
     */
    @Override
    public Familia findById(Long id) {
        Session session = HibernateUtil.getSession();
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
     * Busca familias en la base de datos que coincidan con una ciudad específica.
     *
     * @param ciudad Nombre de la ciudad.
     * @return Lista de familias que residen en la ciudad especificada.
     * @throws HibernateException Si ocurre un error al realizar la consulta.
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
     * Crea una nueva familia en la base de datos.
     *
     * @param familia Objeto {@link Familia} que se desea crear.
     * @return La familia creada.
     * @throws HibernateException Si ocurre un error al realizar la operación.
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
     * Actualiza los datos de una familia existente en la base de datos.
     *
     * @param familia Objeto {@link Familia} con los datos actualizados.
     * @return La familia actualizada.
     * @throws HibernateException Si ocurre un error al realizar la operación.
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
     * Elimina una familia de la base de datos utilizando su ID.
     *
     * @param id Identificador único de la familia que se desea eliminar.
     * @return {@code true} si la familia fue eliminada, {@code false} si no se encontró.
     * @throws HibernateException Si ocurre un error al realizar la operación.
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author ArnoldUD
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        /**
         * Obtiene las propiedades necesarias que complementa la conexión a la
         * base de datos
         */
        Map<String, String> PROPIEDADES = new HashMap<>();
        PROPIEDADES.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        PROPIEDADES.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/tareadb");
        PROPIEDADES.put("javax.persistence.jdbc.user", "postgres");
        PROPIEDADES.put("javax.persistence.jdbc.password", "Admin");
        PROPIEDADES.put("javax.persistence.schema-generation.database.action", "create");
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("tareaUP", PROPIEDADES);
    }

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) throws PersistenceException {
        EntityTransaction et;
        try {
            et = getEntityManager().getTransaction();
            et.begin();
            getEntityManager().persist(entity);
            et.commit();
            Logger.getLogger(entityClass.getName()).log(Level.INFO, "Se ha insertado");//Propiedades.obtenerMensaje("msg_info_transaccion_insertar"));
        } catch (Exception exception) {
            throw new PersistenceException(exception);//Propiedades.obtenerMensaje("msg_error_transaccion_declinada"), exception);
        }
    }

    public void edit(T entity) throws PersistenceException {
        EntityTransaction et;
        try {
            et = getEntityManager().getTransaction();
            et.begin();
            getEntityManager().merge(entity);
            getEntityManager().flush();
            et.commit();
            Logger.getLogger(entityClass.getName()).log(Level.INFO, "Se ha actualizado");//Propiedades.obtenerMensaje("msg_info_transaccion_actualizar"));
        } catch (Exception exception) {
            throw new PersistenceException(exception);//Propiedades.obtenerMensaje("msg_error_transaccion_declinada"), exception);
        }
    }

    public void remove(T entity) throws PersistenceException {
        EntityTransaction et;
        try {
            et = getEntityManager().getTransaction();
            et.begin();
            getEntityManager().remove(getEntityManager().merge(entity));
            et.commit();
            Logger.getLogger(entityClass.getName()).log(Level.INFO, "Se ha eliminado");//Propiedades.obtenerMensaje("msg_info_transaccion_eliminar"));
        } catch (Exception exception) {
            throw new PersistenceException(exception);//Propiedades.obtenerMensaje("msg_error_transaccion_declinada"), exception);
        }
    }

    public T find(Object id) throws PersistenceException {
        try {
            T encontrado = getEntityManager().find(entityClass, id);
            Logger.getLogger(entityClass.getName()).log(Level.INFO, "Se encontr\u00f3 el id = {0} del objeto = {1}", new Object[]{id, entityClass.getName()});//"{0} {1}.{2}.id={3}", new Object[]{Propiedades.obtenerMensaje("msg_info_transaccion_consultar"), nombreBaseDatos, claseEntidad.getSimpleName(), id});
            return encontrado;
        } catch (Exception exception) {
            throw new PersistenceException(exception);//Propiedades.obtenerMensaje("msg_error_transaccion_declinada"), exception);
        }
    }

    public List<T> findAll() throws PersistenceException {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            List<T> list = new ArrayList<>(getEntityManager().createQuery(cq).getResultList());
            Logger.getLogger(entityClass.getName()).log(Level.INFO, "Se encontraron {0} registros", list.size());
            return list;
        } catch (Exception exception) {
            throw new PersistenceException(exception);
        }
    }

    public List<T> findRange(int[] range) {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(range[1] - range[0] + 1);
            q.setFirstResult(range[0]);
            List<T> list = new ArrayList<>(q.getResultList());
            Logger.getLogger(entityClass.getName()).log(Level.INFO, "Se encontraron {0} registros", list.size());
            return new ArrayList<>(q.getResultList());
        } catch (Exception exception) {
            throw new PersistenceException(exception);
        }
    }

    public List<T> buscarPorConsulta(String nombreConsulta, Object... valorParametros) throws PersistenceException {
        TypedQuery<T> tq = null;
        try {
            if (nombreConsulta != null && !nombreConsulta.isEmpty()) {
                tq = getEntityManager().createNamedQuery(nombreConsulta, entityClass);
                for (int i = 0; valorParametros != null && valorParametros.length > 0 && valorParametros[i] != null && i < valorParametros.length; i++) {
                    tq.setParameter(i + 1, valorParametros[i]);
                }
            }
            Logger.getLogger(entityClass.getName()).log(Level.INFO, "Se realiz\u00f3 la consulta {0}", nombreConsulta);//"{0} {1}.{2}", new Object[]{Propiedades.obtenerMensaje("msg_info_transaccion_consultar"), nombreBaseDatos, nombreConsulta});
            return tq != null ? tq.getResultList() : null;
        } catch (Exception exception) {
            throw new PersistenceException(exception);//Propiedades.obtenerMensaje("msg_error_transaccion_declinada"), exception);
        }
    }

    public int count() throws PersistenceException {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(getEntityManager().getCriteriaBuilder().count(rt));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            int cantidad = ((Long) q.getSingleResult()).intValue();
            Logger.getLogger(entityClass.getName()).log(Level.INFO, "Se contaron {0}", cantidad);//"{0} {1}.{2}", new Object[]{Propiedades.obtenerMensaje("msg_info_transaccion_consultar"), nombreBaseDatos, nombreConsulta});
            return cantidad;
        } catch (Exception exception) {
            throw new PersistenceException(exception);
        }
    }

    public Long cantidadRegistros(String nombreConsulta, Object... valorParametros) throws PersistenceException {
        TypedQuery tq = null;
        try {
            if (nombreConsulta != null && !nombreConsulta.isEmpty()) {
                tq = getEntityManager().createNamedQuery(nombreConsulta, entityClass);
                for (int i = 0; valorParametros != null && valorParametros.length > 0 && valorParametros[i] != null && i < valorParametros.length; i++) {
                    tq.setParameter(i + 1, valorParametros[i]);
                }
            }
            Logger.getLogger(entityClass.getName()).log(Level.INFO, "La cantidad de registros seg\u00fan tipo de consulta {0}", nombreConsulta);//"{0} {1}.{2}", new Object[]{Propiedades.obtenerMensaje("msg_info_transaccion_consultar"), nombreBaseDatos, nombreConsulta});
            return tq != null ? tq.getResultList().size() : 0L;
        } catch (Exception exception) {
            throw new PersistenceException(exception);//Propiedades.obtenerMensaje("msg_error_transaccion_declinada"), exception);
        }
    }
}

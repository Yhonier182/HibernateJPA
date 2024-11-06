
package com.yhonier.dao;
import com.yhonier.aplicacion.JPAUtil;
import com.yhonier.entidades.Mascota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MascotaDao {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    public String registrarMascota(Mascota miMascota) {
        entityManager.getTransaction().begin();
        entityManager.persist(miMascota);
        entityManager.getTransaction().commit();
        return "Mascota Registrada!";
    }

    public Mascota consultarMascota(Long idMascota) {
        return entityManager.find(Mascota.class, idMascota);
    }


    public List<Mascota> consultarListaMascotas() {
        TypedQuery<Mascota> query = entityManager.createQuery("SELECT m FROM Mascota m", Mascota.class);
        return query.getResultList();
    } //TypedQuery, permite especificar el tipo de los resultados que se esperan de la consulta


    public List<Mascota> consultarListaMascotasPorSexo(String sexo) {
        String sentencia = "SELECT m FROM Mascota m WHERE m.sexo = :sexoMascota";
        TypedQuery<Mascota> query = entityManager.createQuery(sentencia, Mascota.class);
        query.setParameter("sexoMascota", sexo);
        return query.getResultList();
    }


    public String actualizarMascota(Mascota miMascota) {
        entityManager.getTransaction().begin();
        entityManager.merge(miMascota);
        entityManager.getTransaction().commit();
        return "Mascota Actualizada!" ;
    }

    public String eliminarMascota(Mascota miMascota) {
        entityManager.getTransaction().begin();
        entityManager.remove(miMascota);
        entityManager.getTransaction().commit();
        return "Mascota Eliminada!";
    }

    public void close() {
        entityManager.close();
        JPAUtil.shutdown();
    }

}

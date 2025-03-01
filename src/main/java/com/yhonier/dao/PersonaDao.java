package com.yhonier.dao;

import com.yhonier.aplicacion.JPAUtil;
import com.yhonier.entidades.Persona;
import jakarta.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class PersonaDao {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();


    public String registrarPersona(Persona miPersona) {
        entityManager.getTransaction().begin();
        entityManager.persist(miPersona);
        entityManager.getTransaction().commit();
        return "Registro exitoso";
    }

    public Persona consultarPersona(Long idPersona) {
        Persona miPersona = entityManager.find(Persona.class, idPersona);

        if (miPersona != null) {
            return miPersona;
        } else {
            return null;
        }
    }


    public List<Persona> consultarListaPersonas() {
        List<Persona> Listapersonas = entityManager.createQuery("SELECT p FROM Persona p").getResultList();
        return Listapersonas;
    }

    public String actualizarPersona(Persona miPersona) {
        entityManager.getTransaction().begin();
        entityManager.merge(miPersona);
        entityManager.getTransaction().commit();
        return "Actualizacion exitosa";
    }

    public String eliminarPersona(Persona idPersona) {
        try {
            entityManager.getTransaction().begin();
            Persona miPersona = entityManager.find(Persona.class, idPersona);
            entityManager.remove(miPersona);
            entityManager.getTransaction().commit();
            return "Eliminacion exitosa";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar la persona"
                    + "Verifique  que no tenga registros pendientes", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return "";
    }

    public void close() {
        entityManager.close();
        JPAUtil.shutdown();
    }
}

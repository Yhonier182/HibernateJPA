package com.yhonier.clases;

import com.yhonier.dao.PersonaDao;
import com.yhonier.entidades.Nacimiento;
import com.yhonier.entidades.Persona;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class GestionPersonas {

    PersonaDao mipersonaDao = new PersonaDao();

    public GestionPersonas() {
        String menu  = "MENU DE OPCIONES - GESTION DE PERSONAS\n\n ";
        menu += "1. Registrar Persona\n";
        menu += "2. Consultar Persona\n";
        menu += "3. Consultar lista de Personas\n";
        menu += "4. Actualizar Persona\n";
        menu += "5. Eliminar Persona\n";
        menu += "6. Salir\n";

        int opc = 0;
        while (opc != 6) {
            opc = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opc) {
                case 1: registrar(); break;
                case 2: consultar(); break;
                case 3: consultarLista(); break;
                case 4: actualizarNombre(); break;
                case 5: eliminar(); break;
                case 6: mipersonaDao.close(); break;
            }
        }
    }


    public void registrar() {
        Persona persona = new Persona();
        persona.setIdPersona(Long.parseLong(JOptionPane.showInputDialog("Ingrese el Documento")));
        persona.setNombre(JOptionPane.showInputDialog("Ingrese el nombre"));
        persona.setTelefono(JOptionPane.showInputDialog("Ingrese el telefono"));
        persona.setProfesion(JOptionPane.showInputDialog("Ingrese la profesion"));
        persona.setTipo(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tipo")));

        persona.setNacimiento(obtenerDatosNacimiento());

        System.out.println(mipersonaDao.registrarPersona(persona));
        System.out.println(persona);
        System.out.println();
    }

    private Nacimiento obtenerDatosNacimiento() {
        int dia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dia de  nacimiento"));
        int mes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el mes de nacimiento"));
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de nacimiento"));

        Nacimiento datosNacimiento = new Nacimiento();

        datosNacimiento.setIdNacimiento(null);
        datosNacimiento.setFechaNacimiento(LocalDate.of(ano,mes,dia));
        datosNacimiento.setCiudadNacimiento(JOptionPane.showInputDialog("Ingrese el ciudad de nacimiento"));
        datosNacimiento.setDepartamentoNacimiento(JOptionPane.showInputDialog("Ingrese el departamento de nacimiento"));
        datosNacimiento.setPaisNacimiento(JOptionPane.showInputDialog("Ingrese el pais de nacimiento"));

        return datosNacimiento;
    }

    public void consultar() {
        Long idPersona = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona"));

        Persona miPersona = mipersonaDao.consultarPersona(idPersona);
        if (miPersona != null){
            System.out.println(miPersona);
            System.out.println();
        }else {
            System.out.println("No existe Persona");
        }
        System.out.println();
    }

    public void consultarLista() {
        System.out.println("Lista de personas");
        List<Persona> listapersonas = mipersonaDao.consultarListaPersonas();
        for (Persona persona : listapersonas) {
            System.out.println(persona);
        }
    }

    public void actualizarNombre() {
        Long idPersona = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id para actualizar su nombre"));

        Persona mipersona = mipersonaDao.consultarPersona(idPersona);
        if (mipersona != null){
            System.out.println(mipersona);
            System.out.println();
            mipersona.setNombre(JOptionPane.showInputDialog("Ingrese el nombre"));

            System.out.println(mipersonaDao.actualizarPersona(mipersona));
            System.out.println();
        }else {
            System.out.println("No existe Persona");
        }
        System.out.println();
    }

    private  void eliminar (){
        Long idPersona =Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la persona"));
        Persona mipersona = mipersonaDao.consultarPersona(idPersona);

        if (mipersona != null){
            System.out.println(mipersona);
            System.out.println();

            System.out.println(mipersonaDao.eliminarPersona(mipersona));
            System.out.println();
        }
    }




}

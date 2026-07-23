/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author panch
 */
public class Mascota {
    private int idMascota;
    private String nombre;
    private String raza;
    private String color;
    private int edad;
    private String estadoAdopcion;

    public Mascota() {}

    public Mascota(String nombre, String raza, String color, int edad, String estadoAdopcion) {
        this.nombre = nombre;
        this.raza = raza;
        this.color = color;
        this.edad = edad;
        this.estadoAdopcion = estadoAdopcion;
    }

    public Mascota(int idMascota, String nombre, String raza, String color, int edad, String estadoAdopcion) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.raza = raza;
        this.color = color;
        this.edad = edad;
        this.estadoAdopcion = estadoAdopcion;
    }

    public int getIdMascota(){ 
        return idMascota; 
    }
    public void setIdMascota(int idMascota){ 
        this.idMascota = idMascota; 
    }

    public String getNombre(){ 
        return nombre; 
    }
    public void setNombre(String nombre){ 
        this.nombre = nombre; 
    }

    public String getRaza(){ 
        return raza; 
    }
    public void setRaza(String raza){ 
        this.raza = raza; 
    }

    public String getColor(){ 
        return color; 
    }
    public void setColor(String color){ 
        this.color = color; 
    }

    public int getEdad(){ 
        return edad; 
    }
    public void setEdad(int edad){ 
        this.edad = edad; 
    }

    public String getEstadoAdopcion(){ 
        return estadoAdopcion; 
    }
    public void setEstadoAdopcion(String estadoAdopcion){ 
        this.estadoAdopcion = estadoAdopcion; 
    }
}

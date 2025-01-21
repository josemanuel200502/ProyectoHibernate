package org.example.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "familias")
public class Familia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int edad;
    private String ciudad;

    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animales> animalesAcogidos = new ArrayList<>();

    public Familia() {}

    public Familia(String nombre, int edad, String ciudad) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Animales> getAnimalesAcogidos() {
        return animalesAcogidos;
    }

    public void setAnimalesAcogidos(List<Animales> animalesAcogidos) {
        this.animalesAcogidos = animalesAcogidos;
    }
}

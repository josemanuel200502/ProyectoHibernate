package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "animales")
public class Animales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String especie;

    @Column(nullable = false)
    private int edad;

    @Column(length = 500)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private TipoAnimal tipo;

    public enum TipoAnimal {
        PERROS, GATOS, PAJARITOS, CERDOS_VIETNAMITAS, SERPIENTES, CAMALEONES, ARAÑAS;
    }

    // Constructor vacío
    public Animales() {
    }

    // Constructor parametrizado
    public Animales(String nombre, String especie, int edad, String descripcion, TipoAnimal tipo) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.descripcion = descripcion;
        this.tipo = tipo;
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoAnimal getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnimal tipo) {
        this.tipo = tipo;
    }

    // Métodos
    public void registrarAnimal() {
        System.out.println("Registrando el animal: " + nombre);
    }

    public void buscarEspecie() {
        System.out.println("Buscando especie: " + especie);
    }

    public void actualizarEstado() {
        System.out.println("Actualizando estado del animal: " + nombre);
    }

    @Override
    public String toString() {
        return "Animales{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", edad=" + edad +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}

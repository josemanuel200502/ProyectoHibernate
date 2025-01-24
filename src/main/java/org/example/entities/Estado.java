package org.example.entities;

public enum Estado {
    RECIEN_ABANDONADO("Recién abandonado"),
    EN_REFUGIO("En refugio"),
    PROXIMAMENTE_EN_ACOGIDA("Próximamente en acogida");

    private final String descripcion;

    // Constructor
    Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método para obtener la descripción
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}

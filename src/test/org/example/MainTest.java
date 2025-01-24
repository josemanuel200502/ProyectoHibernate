package org.example;

import org.example.DAO.AnimalesImpl;
import org.example.DAO.FamiliaImpl;
import org.example.entities.Animales;
import org.example.entities.Familia;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class MainTest {
    private AnimalesImpl animalesDAO;
    private FamiliaImpl familiaDAO;
    private Session session;
    private SessionFactory factory;
    private Scanner scanner;


    @BeforeEach
    void setUp() {

        // Inicializar la sesión de Hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Animales.class).buildSessionFactory();
        Session session = factory.openSession();
        animalesDAO = new AnimalesImpl(session);  // Asegúrate de inicializar tu DAO adecuadamente
        familiaDAO = new FamiliaImpl(session);
        scanner = new Scanner(System.in);
    }

    @Test
    void testRegistrarNuevoAnimal() {
        // Datos de prueba
        String nombre = "Victor";
        String especie = "Perro";
        int edad = 3;
        String descripcion = "Recien abandonado";

        // Crear el nuevo animal
        Animales nuevoAnimal = new Animales(nombre, especie, edad, descripcion);

        // Registrar el animal
        animalesDAO.create(nuevoAnimal);

        // Verificar que el animal se ha guardado correctamente
        List<Animales> animales = animalesDAO.findAllByEspecie("Perro");
        Animales  registrado=animales.get(animales.size()-1);
        assertNotNull(registrado);
        assertEquals("Victor", registrado.getNombre());
        assertEquals("Perro", registrado.getEspecie());
        assertEquals(3, registrado.getEdad());
        assertEquals("Recien abandonado", registrado.getDescripcion());
    }


    @Test
    void testBuscarAnimalesPorEspecie() {
        // Datos de prueba
        String especieBusqueda = "Perro";

        // Buscar animales por especie
        List<Animales> animales = animalesDAO.findAllByEspecie(especieBusqueda);

        // Verificar que la búsqueda no esté vacía
        assertFalse(animales.isEmpty());

        // Verificar que todos los animales encontrados son de la especie correcta
        for (Animales animal : animales) {
            assertEquals("Perro", animal.getEspecie());
        }
    }


    @Test
    void testRegistrarFamilia() {
        // Crear un animal y una familia
        Animales animal = new Animales("Max", "Perro", 3, "Recien abandonado");
        Familia familia = new Familia("Pérez", 4, "Madrid");

        // Registrar el animal y la familia
        animalesDAO.create(animal);
        familiaDAO.create(familia);

        // Asociar la familia con el animal
        animal.setFamilia(familia);
        animalesDAO.update(animal);

        // Verificar que la familia esté asociada correctamente al animal
        assertNotNull(animal.getFamilia());
        assertEquals("Pérez", animal.getFamilia().getNombre());
    }

    @Test
    void testBuscarAnimalesPorEdad() {
        // Buscar animales por edad
        int edadBusqueda = 3;
        List<Animales> animales = animalesDAO.findAllByEdad(edadBusqueda);

        // Verificar que la búsqueda no esté vacía
        assertFalse(animales.isEmpty());

        // Verificar que todos los animales encontrados tengan la edad correcta
        for (Animales animal : animales) {
            assertEquals(3, animal.getEdad());
        }
    }

    @Test
    void testBuscarAnimalesPorDescripcion() {
        // Buscar animales por descripción
        String descripcionBusqueda = "Recien abandonado";
        List<Animales> animales = animalesDAO.findAllByDescripcion(descripcionBusqueda);

        // Verificar que la búsqueda no esté vacía
        assertFalse(animales.isEmpty());

        // Verificar que todos los animales encontrados tengan la descripción correcta
        for (Animales animal : animales) {
            assertEquals("Recien abandonado", animal.getDescripcion());
        }
    }


}

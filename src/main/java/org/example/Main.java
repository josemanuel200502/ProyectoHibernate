package org.example;



import org.example.DAO.AnimalesImpl;
import org.example.DAO.FamiliaImpl;
import org.example.entities.Animales;
import org.example.entities.Familia;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Crear la sesión de Hibernate
        Session session = HibernateUtil.getSession();

        // Crear las implementaciones de los DAOs
        AnimalesImpl animalesDAO = new AnimalesImpl(session);
        FamiliaImpl familiaDAO = new FamiliaImpl(session);

        // Crear un scanner para interactuar con el usuario
        Scanner scanner = new Scanner(System.in);

        // Menú de opciones
        int opcion;
        do {
            System.out.println("\n=== Menú Refugio de Animales ===");
            System.out.println("1. Registrar nuevo animal");
            System.out.println("2. Buscar animales por especie");
            System.out.println("3. Buscar animales por edad");
            System.out.println("4. Buscar animales por descripción");
            System.out.println("5. Registrar familia que acoge un animal");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    registrarNuevoAnimal(scanner, animalesDAO);
                    break;
                case 2:
                    buscarAnimalesPorEspecie(scanner, animalesDAO);
                    break;
                case 3:
                    buscarAnimalesPorEdad(scanner, animalesDAO);
                    break;
                case 4:
                    buscarAnimalesPorDescripcion(scanner, animalesDAO);
                    break;
                case 5:
                    registrarFamilia(scanner, animalesDAO, familiaDAO);
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static void registrarNuevoAnimal(Scanner scanner, AnimalesImpl animalesDAO) {
        System.out.println("Ingrese los datos del nuevo animal:");
        System.out.println("Nombre:");
        String nombre= scanner.nextLine();
        System.out.print("Especie(Perro,Gato,Pajarito,Cerdo_vietnamita,serpiente,camaleon,araña:");
        String especie = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Descripción (recién abandonado, tiempo en el refugio, próximamente en acogida): ");
        String descripcion = scanner.nextLine();

        // Aquí ajustas el constructor para crear el nuevo animal
        Animales nuevoAnimal = new Animales(nombre,especie, edad, descripcion);
        animalesDAO.create(nuevoAnimal);
        System.out.println("Animal registrado correctamente.");
    }

    private static void buscarAnimalesPorEspecie(Scanner scanner, AnimalesImpl animalesDAO) {
        System.out.print("Ingrese la especie que desea buscar(Perro,Gato,Pajarito,Cerdo_vietnamita,serpiente,camaleon,araña): ");
        String especieBusqueda = scanner.nextLine();
        List<Animales> animales = animalesDAO.findAllByEspecie(especieBusqueda);
        if (animales.isEmpty()) {
            System.out.println("No se encontraron animales con esa especie.");
        } else {
            animales.forEach(System.out::println);
        }
    }

    private static void buscarAnimalesPorEdad(Scanner scanner, AnimalesImpl animalesDAO) {
        System.out.print("Ingrese la edad de los animales a buscar: ");
        int edadBusqueda = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        List<Animales> animales = animalesDAO.findAllByEdad(edadBusqueda);
        if (animales.isEmpty()) {
            System.out.println("No se encontraron animales con esa edad.");
        } else {
            animales.forEach(System.out::println);
        }
    }

    private static void buscarAnimalesPorDescripcion(Scanner scanner, AnimalesImpl animalesDAO) {
        System.out.print("Ingrese la descripción que desea buscar(recién abandonado, tiempo en el refugio, próximamente en acogida): ");
        String descripcionBusqueda = scanner.nextLine();
        List<Animales> animales = animalesDAO.findAllByDescripcion(descripcionBusqueda);
        if (animales.isEmpty()) {
            System.out.println("No se encontraron animales con esa descripción.");
        } else {
            animales.forEach(System.out::println);
        }
    }

    private static void registrarFamilia(Scanner scanner, AnimalesImpl animalesDAO, FamiliaImpl familiaDAO) {
        System.out.println("Animales disponibles para acoger:");
        List<Animales> animales = animalesDAO.findAll();
        if (animales.isEmpty()) {
            System.out.println("No hay animales disponibles en el refugio.");
            return;
        }

        animales.forEach(System.out::println);

        System.out.print("Ingrese la especie del animal que desea acoger: ");
        String especie = scanner.nextLine();

        // Filtrar el animal por especie y raza
        Animales animalAcojer = animales.stream()
                .filter(a -> a.getEspecie().equalsIgnoreCase(especie))
                .findFirst()
                .orElse(null);

        if (animalAcojer == null) {
            System.out.println("No se encontró un animal con esa especie y raza.");
        } else {
            System.out.println("Ingrese los datos de la familia:");
            System.out.print("Nombre de la familia: ");
            String nombre = scanner.nextLine();
            System.out.print("Edad de la familia: ");
            int edad = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            System.out.print("Ciudad de la familia: ");
            String ciudad = scanner.nextLine();

            Familia nuevaFamilia = new Familia(nombre, edad, ciudad);
            familiaDAO.create(nuevaFamilia);

            animalAcojer.setFamilia(nuevaFamilia);
            animalesDAO.update(animalAcojer);
            System.out.println("La familia ha acogido al animal correctamente.");
        }
    }
}

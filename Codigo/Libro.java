import java.util.*;

public class Libro {
    // Atributos
    private String titulo, autor, resumen, path, isbn13, fechaPublicacion;
    private ArrayList<String> generos;
    private Map<String, Integer> calificaciones;
    private ArrayList<String> reviews;

    // Constructor
    public Libro(String titulo, String autor, String resumen, String path, String isbn13, String fechaPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.resumen = resumen;
        this.path = path;
        this.isbn13 = isbn13;
        this.fechaPublicacion = fechaPublicacion;
        generos = new ArrayList<>();
        calificaciones = new HashMap<>();
        reviews = new ArrayList<>();
    }

    // Constructor adicional
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    // Métodos para géneros
    public void agregarGenero(String genero) {
        generos.add(genero);
    }

    public void eliminarGenero(String genero) {
        generos.remove(genero);
    }

    // Métodos para reseñas y calificaciones
    public void agregarReview(String usuario, String review, int calificacion) {
        if (!calificaciones.containsKey(usuario)) {
            reviews.add(review);
            calificaciones.put(usuario, calificacion);
        } else {
            System.out.println("Ya has dado una calificación para este libro. No puedes calificarlo nuevamente.");
        }
    }

    public void mostrarResenas() {
        System.out.println("Reseñas del libro \"" + titulo + "\":");
        for (int i = 0; i < reviews.size(); i++) {
            System.out.println("Usuario: " + calificaciones.keySet().toArray()[i]);
            System.out.println("Calificación: " + calificaciones.values().toArray()[i]);
            System.out.println("Reseña: " + reviews.get(i));
            System.out.println();
        }
    }

    // Otros métodos relacionados con el libro
    public void mostrarInformacion() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Resumen: " + resumen);
        System.out.println("ISBN: " + isbn13);
        System.out.println("Fecha de Publicación: " + fechaPublicacion);
        System.out.print("Géneros: ");
        for (int i = 0; i < generos.size(); i++) {
            if (i != generos.size() - 1) {
                System.out.print(generos.get(i) + ", ");
            } else {
                System.out.println(generos.get(i));
            }
        }
        System.out.println();
        mostrarResenas();
    }

    // setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setIsbn(String isbn13) {
        this.isbn13 = isbn13;
    }

    public void setfechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    // getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getResumen() {
        return resumen;
    }

    public String getPath() {
        return path;
    }

    public String getIsbn() {
        return isbn13;
    }

    public ArrayList getGeneros() {
        return generos;
    }

}
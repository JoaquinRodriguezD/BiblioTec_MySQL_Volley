package com.example.bibliotec;

public class Libros {

    private String titulo;
    private String autor;
    private String editorial;
    private String descripcion;
    private String imagen;

    public Libros(String titulo, String autor, String editorial, String descripcion, String imagen) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

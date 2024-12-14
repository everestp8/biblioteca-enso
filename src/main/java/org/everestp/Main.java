package org.everestp;

import org.everestp.daos.LivroDAO;
import org.everestp.daos.UsuarioDAO;
import org.everestp.models.Livro;
import org.everestp.models.Usuario;

public class Main {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LivroDAO livroDAO = new LivroDAO();

        Usuario u1 = new Usuario(1, "ceps5@email.com", "123456", "123.123.123-07", 0);
        Usuario u2 = new Usuario(2, "edas1@email.com", "123457", "123.123.123-08", 0);
        Usuario u3 = new Usuario(2, "bgss3@email.com", "123458", "123.123.123-09", 0);
        Livro l1 = new Livro(1, "1984", "George Orwell", "Ficção", "Distopia", 1949);
        Livro l2 = new Livro(2, "A Metamorfose", "F. Kafka", "Novela", "Inseto", 1915);

        usuarioDAO.save(u1);
        usuarioDAO.save(u2);
        usuarioDAO.save(u3);
        livroDAO.save(l1);
        livroDAO.save(l2);

        System.out.println("E-mail dos usuários: ");
        for (Usuario u : usuarioDAO.getAll())
            System.out.println(u.getEmail());
        System.out.println("Títulos dos livros: ");
        for (Livro l : livroDAO.getAll())
            System.out.println(l.getTitulo());
    }
}
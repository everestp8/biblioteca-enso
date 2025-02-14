package org.everestp;

import org.everestp.controllers.UsuarioController;
import org.everestp.daos.EmprestimoDAO;
import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.LivroDAO;
import org.everestp.daos.RenovacaoDAO;
import org.everestp.daos.UsuarioDAO;
import org.everestp.models.Exemplar;
import org.everestp.models.Livro;
import org.everestp.models.Usuario;
import org.everestp.services.EmprestimoService;
import org.everestp.services.ExemplarService;
import org.everestp.services.LivroService;
import org.everestp.services.RenovacaoService;
import org.everestp.services.UsuarioService;
import org.everestp.views_cli.MenuCLI;
import org.everestp.views_gui.TelaPrincipal;

public class Main {
    private static Usuario usuario;

    public static void main(String[] args) {
        DatabaseConnection.createConnection();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LivroDAO livroDAO = new LivroDAO();
        ExemplarDAO exemplarDAO = new ExemplarDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        RenovacaoDAO renovacaoDAO = new RenovacaoDAO();
        
        // Dados de Teste -- START
        usuarioDAO.save(new Usuario(0, "John Doe", "@@@", "123", "cpf1", 0));
        usuario = usuarioDAO.getById(1);

        livroDAO.save(new Livro(0, "l1", "autor1", "genero1", "desc1", 2001));
        livroDAO.save(new Livro(0, "l2", "autor2", "genero2", "desc2", 2002));
        livroDAO.save(new Livro(0, "l3", "autor3", "genero3", "desc3", 2003));

        exemplarDAO.save(new Exemplar(0, 1, "aaabbb", true));
        exemplarDAO.save(new Exemplar(0, 1, "baabbb", false));
        exemplarDAO.save(new Exemplar(0, 2, "aaabbc", false));
        exemplarDAO.save(new Exemplar(0, 2, "baabbc", false));
        exemplarDAO.save(new Exemplar(0, 2, "caabbc", true));
        exemplarDAO.save(new Exemplar(0, 3, "aaabbd", true));
        // Dados de Teste -- END

        UsuarioService usuarioService = new UsuarioService(usuarioDAO);
        LivroService livroService = new LivroService(livroDAO);
        EmprestimoService emprestimoService = new EmprestimoService(emprestimoDAO, exemplarDAO, usuarioDAO);
        ExemplarService exemplarService = new ExemplarService(exemplarDAO, livroDAO);
        RenovacaoService renovacaoService = new RenovacaoService(renovacaoDAO, emprestimoDAO);

        UsuarioController usuarioController = new UsuarioController(usuarioService, emprestimoService);
        
        // MenuCLI menuCLI = new MenuCLI(usuario, usuarioController, livroService, exemplarService, emprestimoService, renovacaoService);
        
        new TelaPrincipal(usuarioController).setVisible(false);
    }
}
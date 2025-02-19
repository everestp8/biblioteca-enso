package org.everestp;

import org.everestp.controllers.EmprestimoController;
import org.everestp.controllers.LivroController;
import org.everestp.controllers.UsuarioController;
import org.everestp.daos.EmprestimoDAO;
import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.LivroDAO;
import org.everestp.daos.RenovacaoDAO;
import org.everestp.daos.UsuarioDAO;
import org.everestp.services.EmprestimoService;
import org.everestp.services.ExemplarService;
import org.everestp.services.LivroService;
import org.everestp.services.RenovacaoService;
import org.everestp.services.UsuarioService;
import org.everestp.views_gui.TelaPrincipal;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.createConnection();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LivroDAO livroDAO = new LivroDAO();
        ExemplarDAO exemplarDAO = new ExemplarDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        RenovacaoDAO renovacaoDAO = new RenovacaoDAO();

        UsuarioService usuarioService = new UsuarioService(usuarioDAO);
        LivroService livroService = new LivroService(livroDAO);
        EmprestimoService emprestimoService = new EmprestimoService(emprestimoDAO, exemplarDAO, usuarioDAO);
        ExemplarService exemplarService = new ExemplarService(exemplarDAO, livroDAO);
        RenovacaoService renovacaoService = new RenovacaoService(renovacaoDAO, emprestimoDAO);

        UsuarioController usuarioController = new UsuarioController(usuarioService, emprestimoService);
        LivroController livroController = new LivroController(livroService, exemplarService);
        EmprestimoController emprestimoController = new EmprestimoController(emprestimoService, renovacaoService);
        
        // MenuCLI menuCLI = new MenuCLI(usuarioController, livroController, emprestimoController);

        new TelaPrincipal(usuarioController, livroController, emprestimoController).setVisible(false);
    }
}
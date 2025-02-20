package org.everestp.views_gui;

import javax.swing.*;

import org.everestp.controllers.EmprestimoController;
import org.everestp.controllers.LivroController;
import org.everestp.controllers.Response;
import org.everestp.controllers.UsuarioController;
import org.everestp.models.Emprestimo;
import org.everestp.models.Exemplar;
import org.everestp.models.Usuario;

import java.util.List;

public class TelaPrincipal extends javax.swing.JFrame {
    private static UsuarioController usuarioController;
    private static LivroController livroController;
    private static EmprestimoController emprestimoController;
    private static Usuario usuario;
    private static javax.swing.JFrame telaAtiva;

    public static void setTelaAtiva(JFrame telaAtiva) {
        if (TelaPrincipal.telaAtiva != null)
            TelaPrincipal.telaAtiva.setVisible(false);
        
        TelaPrincipal.telaAtiva = telaAtiva;
        TelaPrincipal.telaAtiva.setVisible(true);
    }
    
    public static void setPopUp(JFrame telaPopUp) {
        telaPopUp.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        telaPopUp.setVisible(true);
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        TelaPrincipal.usuario = usuario;
    }

    public static UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public static LivroController getLivroController() {
        return livroController;
    }

    public static EmprestimoController getEmprestimoController() {
        return emprestimoController;
    }

    public TelaPrincipal(UsuarioController usuarioController, LivroController livroController, EmprestimoController emprestimoController) {
        TelaPrincipal.usuarioController = usuarioController;
        TelaPrincipal.livroController = livroController;
        TelaPrincipal.emprestimoController = emprestimoController;
        initComponents();
        setTelaAtiva(new TelaLogin());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

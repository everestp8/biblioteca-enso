package org.everestp.utils;

import org.everestp.dtos.EmprestimoDTO;
import org.everestp.dtos.LivroDTO;
import org.everestp.dtos.UsuarioDTO;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

    public static boolean validarEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean validarCPF(String cpf) {
        return cpf != null && CPF_PATTERN.matcher(cpf).matches();
    }

    public static boolean validarTamanhoString(String campo) {
        return campo != null && campo.length() <= 50;
    }

    public static boolean validarTamanhoString(String campo, int tamanho) {
        return campo != null && campo.length() <= tamanho;
    }

    public static boolean validarAno(Integer ano) {
        int anoAtual = java.time.Year.now().getValue();
        return ano != null && ano >= 1000 && ano <= anoAtual;
    }

    public static boolean validarPapel(Integer papel) {
        return papel != null && (papel == 0 || papel == 1 || papel == 2);
    }

    public static boolean validarIdFisico(String idFisico) {
        return idFisico.length() == 6;
    }

    public static boolean validarUsuarioDTO(UsuarioDTO usuarioDTO) {
        return usuarioDTO != null
                && validarTamanhoString(usuarioDTO.nome())
                && validarEmail(usuarioDTO.email())
                && validarTamanhoString(usuarioDTO.senha())
                && validarCPF(usuarioDTO.cpf())
                && validarPapel(usuarioDTO.papel());
    }

    public static boolean validarLivroDTO(LivroDTO livroDTO) {
        return livroDTO != null
                && validarTamanhoString(livroDTO.titulo())
                && validarTamanhoString(livroDTO.autor())
                && validarTamanhoString(livroDTO.genero())
                && validarTamanhoString(livroDTO.descricao(), 200)
                && validarAno(livroDTO.ano());
    }

    public static boolean validarEmprestimoDTO(EmprestimoDTO emprestimoDTO) {
        return emprestimoDTO != null
                && validarTamanhoString(emprestimoDTO.exemplarIdFIsico())
                && validarCPF(emprestimoDTO.cpfUsuario());
    }
}

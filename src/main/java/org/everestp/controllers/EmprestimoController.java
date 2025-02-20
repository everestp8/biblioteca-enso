package org.everestp.controllers;

import org.everestp.dtos.EmprestimoDTO;
import org.everestp.models.Emprestimo;
import org.everestp.models.Exemplar;
import org.everestp.services.EmprestimoService;
import org.everestp.services.ExemplarService;
import org.everestp.services.RenovacaoService;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoController {
    private final EmprestimoService emprestimoService;
    private final RenovacaoService renovacaoService;

    public EmprestimoController(EmprestimoService emprestimoService, RenovacaoService renovacaoService) {
        this.emprestimoService = emprestimoService;
        this.renovacaoService = renovacaoService;
    }

    public Response<List<Emprestimo>> listarEmprestimosDoUsuario(int usuarioId) {
        try {
            List<Emprestimo> emprestimos = this.emprestimoService.getAllEmprestimosByUsuarioId(usuarioId);
            return Response.sucesso(emprestimos);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<List<Emprestimo>> listarEmprestimosEntreDatas(LocalDate dataInicio, LocalDate dataFim) {
        try {
            List<Emprestimo> emprestimos = this.emprestimoService.getAllEmprestimosBetweenDates(dataInicio, dataFim);
            return Response.sucesso(emprestimos);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Integer> quantidadeRenovacoes(int usuarioId, int emprestimoId) {
        try {
            int qRenovacoes = this.renovacaoService.getRenovacoesByUsuarioIdAndEmprestimoId(usuarioId, emprestimoId).size();
            return Response.sucesso(qRenovacoes);
        } catch (Exception e) {
            throw new RuntimeException(e);
            // return Response.falha(e);
        }
    }

    public Response<Void> devolverExemplar(String idFisico) {
        try {
            this.emprestimoService.devolverEmprestimo(idFisico);
            return Response.sucesso();
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Void> renovarEmprestimo(int usuarioId, String idFisico) {
        try {
            this.renovacaoService.renovarEmprestimo(idFisico, usuarioId);
            return Response.sucesso();
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Void> novoEmprestimo(EmprestimoDTO dadosEmprestimo) {
        try {
            this.emprestimoService.fazerEmprestimo(dadosEmprestimo);
            return Response.sucesso();
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Integer> contarEmprestimos() {
        try {
            int quantEmprestimos = this.emprestimoService.countEmprestimos();
            return Response.sucesso(quantEmprestimos);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Integer> contarRenovacoes() {
        try {
            int quantRenovacoes = this.renovacaoService.countRenovacoes();
            return Response.sucesso(quantRenovacoes);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }
}

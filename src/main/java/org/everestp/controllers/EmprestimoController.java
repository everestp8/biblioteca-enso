package org.everestp.controllers;

import org.everestp.dtos.EmprestimoDTO;
import org.everestp.models.Emprestimo;
import org.everestp.models.Exemplar;
import org.everestp.services.EmprestimoService;
import org.everestp.services.ExemplarService;
import org.everestp.services.RenovacaoService;

import java.util.List;

public class EmprestimoController {
    private final EmprestimoService emprestimoService;
    private final ExemplarService exemplarService;
    private final RenovacaoService renovacaoService;

    public EmprestimoController(EmprestimoService emprestimoService, ExemplarService exemplarService, RenovacaoService renovacaoService) {
        this.emprestimoService = emprestimoService;
        this.exemplarService = exemplarService;
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

    public Response<Exemplar> exemplarPorId(int exemplarId) {
        try {
            Exemplar exemplar = this.exemplarService.getExemplarById(exemplarId);
            return Response.sucesso(exemplar);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Integer> quantidadeRenovacoes(int usuarioId, int emprestimoId) {
        try {
            int qRenovacoes = this.renovacaoService.getRenovacoesByUsuarioIdAndEmprestimoId(usuarioId, emprestimoId).size();
            return Response.sucesso(qRenovacoes);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Void> devolverExemplar(int usuarioId, String idFisico) {
        try {
            this.emprestimoService.devolverEmprestimo(idFisico, usuarioId);
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
}

package br.com.jogadores.controller;

import br.com.jogadores.entidades.Jogador;
import br.com.jogadores.excecoes.JogadorNaoEncontradoException;
import br.com.jogadores.persistencia.JogadorDAO;

import jakarta.persistence.EntityManager;

public class JogadorController {
    private JogadorDAO jogadorDAO;

    public JogadorController(EntityManager entityManager) {

        this.jogadorDAO = new JogadorDAO(entityManager);
    }

    public Jogador buscarJogadorPorId(int id) {

        try {
            Jogador jogador = jogadorDAO.buscarPorId(id);
            return jogador;

        } catch (JogadorNaoEncontradoException e) {
            System.out.println("\nJogador não encontrado: " + e.getMessage());
            return null;

        }
    }
}

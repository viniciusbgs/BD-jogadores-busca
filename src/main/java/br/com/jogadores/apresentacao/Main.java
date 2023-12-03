package br.com.jogadores.apresentacao;

import br.com.jogadores.controller.JogadorController;
import br.com.jogadores.excecoes.JogadorNaoEncontradoException;
import br.com.jogadores.entidades.Jogador;


import br.com.jogadores.persistencia.JogadorDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;


public class Main {

    public static void main(String args[]) {



        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bd_jpa_jogador_h2");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        JogadorController jogadorController = new JogadorController(entityManager);

        Jogador jogador = new Jogador("s1mple", "awper", "navi", "global");
        Jogador jogador2 = new Jogador("device", "rifler", "astralis", "global");
        Jogador jogador3 = new Jogador("niko", "rifler", "g2", "global");
        Jogador jogador4 = new Jogador("fallen", "awper", "mibr", "global");

        //INSERÇÃO DE JOGADORES
        JogadorDAO jogadorDAO = new JogadorDAO(entityManager);
        jogadorDAO.inserirJogador(jogador);
        jogadorDAO.inserirJogador(jogador2);
        jogadorDAO.inserirJogador(jogador3);
        jogadorDAO.inserirJogador(jogador4);


        List<Jogador> jogadores = jogadorDAO.listarJogadores();

        imprimirJogadores(jogadores);

        System.out.println("--------------------------------------------");
        System.out.println("\nBUSCA DE JOGADORES POR ID:\n");
        System.out.println("--------------------------------------------");


        //BUSCA POR ID QUE DEU CERTO

            System.out.println("Busca por id = 1");
            Jogador jogadorEncontrado = jogadorController.buscarJogadorPorId(1);
            if (jogadorEncontrado != null){
            imprimirJogador(jogadorEncontrado);
            }

            System.out.println("Busca por id = 2");
            Jogador jogadorEncontrado2 = jogadorController.buscarJogadorPorId(2);
            if (jogadorEncontrado2 != null) {
                imprimirJogador(jogadorEncontrado2);
            }


        //BUSCA POR ID QUE DEU ERRADO

            System.out.println("Busca por id = 5");
            Jogador jogadorEncontrado3 = jogadorController.buscarJogadorPorId(5);
            if(jogadorEncontrado3 != null) {
                imprimirJogador(jogadorEncontrado3);
            }
    }


        private static void imprimirJogadores (List < Jogador > jogadores) {
            System.out.println("--------------------------------------------");
            System.out.println("\nLISTA DE JOGADORES:\n");
            System.out.println("--------------------------------------------");
            for (Jogador jogador : jogadores) {
                System.out.println("Id: " + jogador.getId());
                System.out.println("Nome: " + jogador.getNome());
                System.out.println("Função: " + jogador.getFuncao());
                System.out.println("Time: " + jogador.getTime());
                System.out.println("Patente: " + jogador.getPatente());
                System.out.println("\n");
            }
        }

        //imprimir jogador
        private static void imprimirJogador (Jogador jogador){
            System.out.println("\nJogador: \n");
            System.out.println("Id: " + jogador.getId());
            System.out.println("Nome: " + jogador.getNome());
            System.out.println("Função: " + jogador.getFuncao());
            System.out.println("Time: " + jogador.getTime());
            System.out.println("Patente: " + jogador.getPatente());
            System.out.println("\n");
    }
}
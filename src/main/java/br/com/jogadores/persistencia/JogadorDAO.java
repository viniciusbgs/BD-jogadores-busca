package br.com.jogadores.persistencia;

import br.com.jogadores.entidades.Jogador;
import br.com.jogadores.excecoes.JogadorNaoEncontradoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import jakarta.persistence.Query;
import java.util.List;
public class JogadorDAO {
    private EntityManager entityManager;

    public JogadorDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Jogador buscarPorId(int id) throws JogadorNaoEncontradoException {

        Jogador jogador = entityManager.find(Jogador.class, id);
        if (jogador == null) {
            throw new JogadorNaoEncontradoException("ID não existe!");
        }
        return jogador;
    }

    public void inserirJogador(Jogador jogador) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(jogador);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Jogador> listarJogadores() {
        Query query = entityManager.createQuery("SELECT j FROM Jogador j");
        List<Jogador> jogadores = query.getResultList();
        return jogadores;
    }
}

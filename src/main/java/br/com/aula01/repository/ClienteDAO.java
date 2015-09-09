/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aula01.repository;

import br.com.aula01.model.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author UFMS
 */
public class ClienteDAO {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    public ClienteDAO() {
        emf = Persistence.createEntityManagerFactory("Loja01PU");

        em = emf.createEntityManager();
    }

    public void salvar(Cliente cliente) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (cliente.getId() == null) {
                em.persist(cliente);
            } else {
                em.merge(cliente);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

   
    public Cliente buscarPorId(Long id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> buscarTodos() {
        Query consulta = em.createQuery("select c from Cliente AS c order by c.id");

        return consulta.getResultList();
    }

    public void excluir(Cliente cliente) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.remove(cliente);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public List<Cliente> buscaPorNome(String nome) {
        Query q = em.createQuery("select c from Cliente c where c.nome=:nomeAD");
        q.setParameter("nomeAD", nome);

        return q.getResultList();
    }

}

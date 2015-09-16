/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aula01.repository;

import br.com.aula01.model.Cliente;
import br.com.aula01.model.Venda;
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
public class VendaDAO {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    public VendaDAO() {
        emf = Persistence.createEntityManagerFactory("Loja01PU");

        em = emf.createEntityManager();
    }

    public void salvar(Venda venda) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (venda.getId() == null) {
                em.persist(venda);
            } else {
                em.merge(venda);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

   
    public Venda buscarPorId(Long id) {
        return em.find(Venda.class, id);
    }

    public List<Venda> buscarTodos() {
        //Query consulta = em.createQuery("select c from Cliente AS c order by c.id");

        Query consulta = em.createNamedQuery("Venda.findAll");
       

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
        Query q = em.createQuery("select c from Venda c where c.nome=:nomeAD");
        q.setParameter("nomeAD", nome);

        return q.getResultList();
    }

}

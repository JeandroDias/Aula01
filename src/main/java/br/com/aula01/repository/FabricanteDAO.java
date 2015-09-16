/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aula01.repository;

import br.com.aula01.model.Cliente;
import br.com.aula01.model.Fabricante;
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
public class FabricanteDAO {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    public FabricanteDAO() {
        emf = Persistence.createEntityManagerFactory("Loja01PU");

        em = emf.createEntityManager();
    }

    public void salvar(Fabricante fabricante) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (fabricante.getId() == null) {
                em.persist(fabricante);
            } else {
                em.merge(fabricante);
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
        //Query consulta = em.createQuery("select c from Cliente AS c order by c.id");

        Query consulta = em.createNamedQuery("Produto.findAll");
       

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

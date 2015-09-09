/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aula01.view;

import br.com.aula01.model.Cliente;
import br.com.aula01.repository.ClienteDAO;
import java.util.List;

/**
 *
 * @author UFMS
 */
public class Principal {
    
    public static void main(String[] args) {
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("Loja01PU");
        
        Cliente c = new Cliente();
        
        c.setNome("Marcelo");
        
        ClienteDAO clienteDAO = new ClienteDAO();
        
        //clienteDAO.salvar(c);
        
        System.out.println("ID do cliente: "+c.getId());
                
            
        List<Cliente> clientes = clienteDAO.buscarTodos();
        
        for(Cliente cl : clientes){
            System.out.println("Nome Cliente: "+ cl.getNome());
            
        }
        
        
        
        Cliente cliente1 = clienteDAO.buscarPorId((long)3);
        
        System.out.println("Buscar por id 3: "+cliente1.getNome());
        
        
        
        
        
    }
          
    
    
}

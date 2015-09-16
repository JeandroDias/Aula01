/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aula01.view;

import br.com.aula01.model.Fabricante;
import br.com.aula01.model.ItensPedido;
import br.com.aula01.model.Produto;
import br.com.aula01.model.Venda;
import br.com.aula01.repository.FabricanteDAO;
import br.com.aula01.repository.ProdutoDAO;
import br.com.aula01.repository.VendaDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UFMS
 */
public class Principal {

    public static void main(String[] args) {
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("Loja01PU");

        Fabricante fabricante = new Fabricante();
        fabricante.setNome("Nike");
        FabricanteDAO fabDAO = new FabricanteDAO();
        fabDAO.salvar(fabricante);

        Produto produto1 = new Produto();
        produto1.setNome("Tenis1");
        produto1.setQuantidadeEstoque(10);
        produto1.setValor(20.2);
        produto1.setFabricante(fabricante);

        Produto produto2 = new Produto();
        produto2.setNome("Tenis2");
        produto2.setQuantidadeEstoque(10);
        produto2.setValor(20.2);
        produto2.setFabricante(fabricante);

        Produto produto3 = new Produto();
        produto3.setNome("Tenis3");
        produto3.setQuantidadeEstoque(10);
        produto3.setValor(20.2);
        produto3.setFabricante(fabricante);

        ProdutoDAO pDAO = new ProdutoDAO();
        pDAO.salvar(produto1);
        pDAO.salvar(produto2);
        pDAO.salvar(produto3);

        List<ItensPedido> listaItens = new ArrayList<ItensPedido>();
        Venda venda = new Venda();
        
        ItensPedido iten1 = new ItensPedido();
        iten1.setProduto(produto1);
        iten1.setQuantidade(2);
        iten1.setVenda(venda);
        int qtd = 2;
        iten1.setValor(produto1.getValor() * qtd);

        ItensPedido iten2 = new ItensPedido();
        iten2.setProduto(produto1);
        iten2.setQuantidade(2);
        iten2.setVenda(venda);
        int qtd2 = 2;
        iten2.setValor(produto1.getValor() * qtd2);

        ItensPedido iten3 = new ItensPedido();
        iten3.setProduto(produto1);
        iten3.setQuantidade(2);
        iten3.setVenda(venda);
        int qtd3 = 2;
        iten3.setValor(produto1.getValor() * qtd3);

        listaItens.add(iten1);
        listaItens.add(iten2);
        listaItens.add(iten3);

        venda.setItensPedidos(listaItens);

        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.salvar(venda);

        Venda venda2 = vendaDAO.buscarPorId((long) 30);

        for (ItensPedido iten : venda2.getItensPedidos()) {
            System.out.println("Iten: " + iten.getProduto().getNome());
        }
        //Teste
    }

}

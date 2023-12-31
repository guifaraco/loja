package br.com.guilherme.loja.testes;

import br.com.guilherme.loja.dao.CategoriaDao;
import br.com.guilherme.loja.dao.ClienteDao;
import br.com.guilherme.loja.dao.PedidoDao;
import br.com.guilherme.loja.dao.ProdutoDao;
import br.com.guilherme.loja.modelo.*;
import br.com.guilherme.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformanceConsultas {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido = pedidoDao.buscarPedidoComCliente(1L);
        em.close();
        System.out.println(pedido.getCliente().getNome());


    }

    public static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto(
                "Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Produto videogame = new Produto(
                "PS5", "Playstation 5", new BigDecimal("3500"), videogames);
        Produto macbook = new Produto(
                "Macbook", "Macbook pro", new BigDecimal("8500"), informatica);

        Cliente cliente = new Cliente("Guilherme", "123456");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, celular));
        pedido.adicionarItem(new ItemPedido(40, pedido, videogame));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarItem(new ItemPedido(2, pedido, macbook));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);


        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);

        clienteDao.cadastrar(cliente);

        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);


        em.getTransaction().commit();
        em.close();
    }
}

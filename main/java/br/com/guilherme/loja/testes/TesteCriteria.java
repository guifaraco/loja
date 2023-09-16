package br.com.guilherme.loja.testes;

import br.com.guilherme.loja.dao.CategoriaDao;
import br.com.guilherme.loja.dao.ClienteDao;
import br.com.guilherme.loja.dao.ProdutoDao;
import br.com.guilherme.loja.modelo.Categoria;
import br.com.guilherme.loja.modelo.Cliente;
import br.com.guilherme.loja.modelo.Produto;
import br.com.guilherme.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteCriteria {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        produtoDao.buscarPorParametrosComCriteria(null, null, LocalDate.now());
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

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);

        clienteDao.cadastrar(cliente);


        em.getTransaction().commit();
        em.close();
}
}

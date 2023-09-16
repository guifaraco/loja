package br.com.guilherme.loja.testes;

import br.com.guilherme.loja.dao.CategoriaDao;
import br.com.guilherme.loja.dao.ProdutoDao;
import br.com.guilherme.loja.modelo.Categoria;
import br.com.guilherme.loja.modelo.CategoriaId;
import br.com.guilherme.loja.modelo.Produto;
import br.com.guilherme.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1L);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        System.out.println(precoDoProduto);
    }

    public static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");

        Produto celular = new Produto(
                "Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);


        em.getTransaction().commit();

        em.find(Categoria.class, new CategoriaId("CELULARES", "xpto"));

        em.close();
    }
}

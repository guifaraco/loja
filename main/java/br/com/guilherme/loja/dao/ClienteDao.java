package br.com.guilherme.loja.dao;


import br.com.guilherme.loja.modelo.Cliente;
import br.com.guilherme.loja.modelo.Pedido;

import javax.persistence.EntityManager;

public class ClienteDao {
    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

//    public void atualizar(Pedido pedido) {
//        this.em.merge(pedido);
//    }
//
//    public void remover(Pedido pedido) {
//        pedido = em.merge(pedido);
//        this.em.remove(pedido);
//    }
//
    public Cliente buscarPorId(Long id) {
        return em.find(Cliente.class, id);
    }
//
//    public List<Pedido> buscarTodos() {
//        String jpql = "SELECT p FROM Produto p";
//        return em.createQuery(jpql, Pedido.class).getResultList();
//    }
//
//    public List<Pedido> buscarPorNome(String nome) {
//        String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
//        return em.createQuery(jpql, Pedido.class)
//                .setParameter(1, nome)
//                .getResultList();
//    }
//    public List<Pedido> buscarPorNomeDaCategoria(String nome) {
//        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
//        return em.createQuery(jpql, Pedido.class)
//                .setParameter(1, nome)
//                .getResultList();
//    }
//    public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
//        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
//        return em.createQuery(jpql, BigDecimal.class)
//                .setParameter(1, nome)
//                .getSingleResult();
//    }
}

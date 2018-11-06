package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacoesPorCategoria {
	public static void main(String[] args) {
		
		EntityManager entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();

		Categoria categoria = new Categoria();
		categoria.setId(1);

		String jpql = "select m from Movimentacao m join m.categorias c where c = :pCategoria";

		TypedQuery<Movimentacao> query = entityManager.createQuery(jpql, Movimentacao.class);
		query.setParameter("pCategoria", categoria);

		List<Movimentacao> resultados = query.getResultList();

		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
			System.out.println("Conta.valor: " + movimentacao.getValor());
		}

		entityManager.getTransaction().commit();
		entityManager.close();

	}
}

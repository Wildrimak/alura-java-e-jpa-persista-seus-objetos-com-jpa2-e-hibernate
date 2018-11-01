package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPQL {

	public static void main(String[] args) {

		EntityManager entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);

		String jpql = "select m from Movimentacao m where m.conta = :pConta" + " and tipo = :pTipo"
				+ " order by m.valor desc";

		Query query = entityManager.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

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

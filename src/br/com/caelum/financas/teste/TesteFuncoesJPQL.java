package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteFuncoesJPQL {

	public static void main(String[] args) {

		EntityManager entityManager = new JPAUtil().getEntityManager();
		MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(entityManager);

		entityManager.getTransaction().begin();
		
		Conta conta_1 = new Conta();
		conta_1.setId(1);
		Conta conta_2 = new Conta();
		conta_2.setId(2);

		String jpql_sum = "select sum(m.valor) from Movimentacao m where m.conta = :pConta" + " and tipo = :pTipo";
		String jpql_avg = "select avg(m.valor) from Movimentacao m where m.conta = :pConta" + " and tipo = :pTipo";
		String jpql_max = "select max(m.valor) from Movimentacao m where m.conta = :pConta";
		String jpql_count = "select count(m) from Movimentacao m where m.conta = :pConta";


		Query query_sum = entityManager.createQuery(jpql_sum);
		query_sum.setParameter("pConta", conta_2);
		query_sum.setParameter("pTipo", TipoMovimentacao.SAIDA);

		Query query_avg = entityManager.createQuery(jpql_avg);
		query_avg.setParameter("pConta", conta_2);
		query_avg.setParameter("pTipo", TipoMovimentacao.SAIDA);

		Query query_max = entityManager.createQuery(jpql_max);
		query_max.setParameter("pConta", conta_1);

		Query query_count = entityManager.createQuery(jpql_count);
		query_count.setParameter("pConta", conta_2);


		BigDecimal result_sum = (BigDecimal) query_sum.getSingleResult();
		Double result_avg = (Double) query_avg.getSingleResult();
		BigDecimal result_max = (BigDecimal) query_max.getSingleResult();
		Long result_count = (long) query_count.getSingleResult();

		System.out.println("A soma é: " + result_sum);
		System.out.println("A media é: " + result_avg);
		System.out.println("O valor máximo é: " + result_max);
		System.out.println("A quantidade de movimentações da conta 2 é: " + result_count);
		
		entityManager.getTransaction().commit();
		
		List<Double> medias = movimentacaoDAO.getMediasPorDiaETipo(TipoMovimentacao.SAIDA, conta_2);
		
		entityManager.close();
		
		
		for (Double media_diaria : medias) {
			System.out.println("A media das transações por dia é: " + media_diaria);
		}
		
		System.out.println("A media de 01/11/18: " + medias.get(0));
		System.out.println("A media de 06/11/18: " + medias.get(1));



	}

}

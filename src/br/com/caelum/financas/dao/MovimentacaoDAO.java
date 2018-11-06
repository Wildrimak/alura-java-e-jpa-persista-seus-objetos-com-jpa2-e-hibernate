package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class MovimentacaoDAO {
	
	private EntityManager entityManager;
	
	public MovimentacaoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta_2) {
		entityManager.getTransaction().begin();

		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta "
				+ " and tipo = :pTipo" + " group by day(m.data), month(m.data), year(m.data)";

		TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta_2);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		List<Double> medias = (List<Double>) query.getResultList();
		
		entityManager.getTransaction().commit();

		return medias;
	}

}

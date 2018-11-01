package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaContaCliente {
	
	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setId(2);
		
		Cliente cliente = new Cliente();
		cliente.setNome("Leonardo");
		cliente.setEndereco("Rua Fulano, 123");
		cliente.setProfissao("Professor");
		
		cliente.setConta(conta);
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(cliente);
		
		em.getTransaction().commit();

	}
}

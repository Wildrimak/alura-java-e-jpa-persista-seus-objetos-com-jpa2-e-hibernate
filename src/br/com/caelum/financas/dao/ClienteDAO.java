package br.com.caelum.financas.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Cliente;

public class ClienteDAO {

	private EntityManager entityManager;

	public ClienteDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Cliente> getClientes() {
		
		this.entityManager.getTransaction().begin();
		
		String querySelectTodosOsClientesEmJpql = "select c from Cliente c";
		TypedQuery<Cliente> typedQuery = this.entityManager.createQuery(querySelectTodosOsClientesEmJpql, Cliente.class);
		List<Cliente> clientes = typedQuery.getResultList();
		
		this.entityManager.getTransaction().commit();

		return clientes;
	}

	public List<Cliente> getClientesDoBanco(String nomeDoBanco) {
		
		this.entityManager.getTransaction().begin();
		
		//Query esta como se as duas tabelas nao fossem relacionadas entre si, corrigir isso depois
		String querySelectClientesDoBanco = "select cl from Cliente cl, Conta co where cl.conta.id = co.id and co.banco like :pNomeDoBanco";
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(querySelectClientesDoBanco, Cliente.class);
		typedQuery.setParameter("pNomeDoBanco", "%"+nomeDoBanco+"%");
		
		List<Cliente> clientes = typedQuery.getResultList();
		
		this.entityManager.getTransaction().commit();
		
		return clientes;
	}
	
	public List<Cliente> getClientesDaAgencia(String nomeDaAgencia) {
		
		this.entityManager.getTransaction().begin();

		String query = "select cl from Cliente cl, Conta co where cl.conta.id = co.id and co.agencia like :pNomeDaAgencia";
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(query, Cliente.class);
		typedQuery.setParameter("pNomeDaAgencia", "%"+nomeDaAgencia+"%");
		
		List<Cliente> clientes = typedQuery.getResultList();
		
		this.entityManager.getTransaction().commit();
		return clientes;
	}
	
	public Cliente getClientePorNumeroDaConta(String numero){
		
		this.entityManager.getTransaction().begin();
		
		String query = "select cl from Cliente cl, Conta co where cl.conta.id = co.id and co.numero like :pNumeroDaAgencia";
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(query, Cliente.class);
		typedQuery.setParameter("pNumeroDaAgencia", numero);
		Cliente cliente;

		try {
			cliente = typedQuery.getSingleResult();
		} catch (NoResultException noResultException) {
			cliente = null;
		}

		this.entityManager.getTransaction().commit();
		return cliente;
	}
	
	public Cliente getClientePorId(Integer id){
		
		this.entityManager.getTransaction().begin();

		String query = "select cl from Cliente cl where cl.id = :pId";
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(query, Cliente.class);
		typedQuery.setParameter("pId", id);
		Cliente cliente;
		
		try {
			cliente = typedQuery.getSingleResult();
		} catch (Exception e) {
			cliente = null;
		}
		
		this.entityManager.getTransaction().commit();
		return cliente;
	}
	
	public List<Cliente> getClientesCujoTitularContenha(String nome){
		List<Cliente> clientes = null;
		return clientes;
	}
	
	public List<Cliente> getClientesComMovimentacoesFinanceirasAcimaDe(double valor){
		List<Cliente> clientes = null;
		return clientes;
	}
	
	public List<Cliente> getClientesComMovimentacoesFinanceirasAbaixoDe(double valor){
		List<Cliente> clientes = null;
		return clientes;
	}
	
	public List<Cliente> getClientesCujasMovimentacoesForamEntradas(){
		List<Cliente> clientes = null;
		return clientes;
	}
	
	public List<Cliente> getClientesCujasMovimentacoesForamSaidas(){
		List<Cliente> clientes = null;
		return clientes;
	}
	
	public List<Cliente> getClientesCujasMovimentacoesForamFeitasNaData(Calendar data){
		List<Cliente> clientes = null;
		return clientes;
	}
	
	public List<Cliente> getClientesCujasMovimentacoesForamFeitasAntesDaData(Calendar data){
		List<Cliente> clientes = null;
		return clientes;
	}
	
	public List<Cliente> getClientesCujasMovimentacoesForamFeitasDepoisDaData(Calendar data){
		List<Cliente> clientes = null;
		return clientes;
	}
	
	public List<Cliente> getClientesCujasCategoriasForam(Categoria... categorias){
		List<Cliente> clientes = null;
		return clientes;
	}
}

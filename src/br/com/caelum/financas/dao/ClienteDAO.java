package br.com.caelum.financas.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
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
		
		String querySelectClientesDoBanco = "select cl from Cliente cl join Conta co where co.banco like(:pNomeDoBanco)";
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(querySelectClientesDoBanco, Cliente.class);
		typedQuery.setParameter("pNomeDoBanco", nomeDoBanco);
		List<Cliente> clientes = typedQuery.getResultList();
		
		this.entityManager.getTransaction().commit();
		
		return clientes;
	}
	
	public List<Cliente> getClientesDaAgencia(String nomeDaAgencia) {
		return null;
	}
	
	public Cliente getClientePorNumero(String numero){
		return null;
	}
	
	public Cliente getClientePorId(Integer id){
		return null;
	}
	
	public List<Cliente> getClientesCujoTitularContenha(String nome){
		return null;
	}
	
	public List<Cliente> getClientesComMovimentacoesFinanceirasAcimaDe(double valor){
		return null;
	}
	
	public List<Cliente> getClientesComMovimentacoesFinanceirasAbaixoDe(double valor){
		return null;
	}
	
	public List<Cliente> getClientesCujasMovimentacoesForamEntradas(){
		return null;
	}
	
	public List<Cliente> getClientesCujasMovimentacoesForamSaidas(){
		return null;
	}
	
	public List<Cliente> getClientesCujasMovimentacoesForamFeitasNaData(Calendar data){
		return null;
	}
	
	public List<Cliente> getClientesCujasMovimentacoesForamFeitasAntesDaData(Calendar data){
		return null;
	}
	
	public List<Cliente> getClientesCujasMovimentacoesForamFeitasDepoisDaData(Calendar data){
		return null;
	}
	
	public List<Cliente> getClientesCujasCategoriasForam(Categoria... categorias){
		return null;
	}
}

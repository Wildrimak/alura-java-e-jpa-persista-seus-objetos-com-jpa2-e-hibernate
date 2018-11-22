package br.com.caelum.financas.teste.modelo;

import java.util.List;

import br.com.caelum.financas.dao.ClienteDAO;
import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.util.JPAUtil;

public class TestaClienteDAO {
	
	private final static ClienteDAO clienteDAO = new ClienteDAO(new JPAUtil().getEntityManager());
	
	public static void main(String[] args) {
		
		testaMetodoGetClientes();
		testaMetodoGetClientesDoBanco();
		
	}

	private static void testaMetodoGetClientes() {
		
		List<Cliente> clientes = clienteDAO.getClientes();
		
		System.out.println("\nClientes:\n");
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	
	}

	private static void testaMetodoGetClientesDoBanco() {
		String banco = "CAIXA";
		List<Cliente> clientesDoBanco = clienteDAO.getClientesDoBanco(banco);
		
		System.out.println("\nClientes do banco " + banco);
		for (Cliente cliente : clientesDoBanco) {
			System.out.println(cliente);
		}
		
	}

}

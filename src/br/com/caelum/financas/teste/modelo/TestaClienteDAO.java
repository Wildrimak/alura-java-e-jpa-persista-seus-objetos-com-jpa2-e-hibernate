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
		testaMetodoGetClientesDaAgencia();
		testaMetodoGetClientePorNumeroConta();
		testaMetodoGetClientePorId();
	}

	private static void testaMetodoGetClientes() {

		List<Cliente> clientes = clienteDAO.getClientes();

		System.out.println("\nClientes:\n");
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

	}

	private static void testaMetodoGetClientesDoBanco() {
		String banco = "C";
		List<Cliente> clientesDoBanco = clienteDAO.getClientesDoBanco(banco);

		System.out.println("\nClientes cujos nomes do banco contenham: " + banco + "\n");
		for (Cliente cliente : clientesDoBanco) {
			System.out.println(cliente);
		}

	}

	private static void testaMetodoGetClientesDaAgencia() {
		String agencia = "1";
		List<Cliente> clientesDaAgencia = clienteDAO.getClientesDaAgencia(agencia);

		System.out.println("\nClientes cujos nomes das agencias contenham: " + agencia + "\n");
		for (Cliente cliente : clientesDaAgencia) {
			System.out.println(cliente);
		}
	}

	private static void testaMetodoGetClientePorNumeroConta() {
		// String numero = "12345-6"; Observar que esse é um problema que
		// acontece por não ter as tabelas do banco normalizadas o suficiente
		String numero = "99966-6";
		Cliente cliente = clienteDAO.getClientePorNumeroDaConta(numero);

		System.out.println("\nO cliente cujo numero da conta seja: " + numero + " é: \n");

		if (cliente == null)
			System.out.println("Não há cliente com esse número de conta nas contas cadastradas");
		else
			System.out.println(cliente);
	}

	private static void testaMetodoGetClientePorId() {
		Integer id = 2;

		Cliente cliente = clienteDAO.getClientePorId(id);
		if (cliente != null)
			System.out.println("\nO cliente de id: " + id + " é:\n" + cliente);
		else
			System.out.println("Não há cliente com o id especificado");
	}

}

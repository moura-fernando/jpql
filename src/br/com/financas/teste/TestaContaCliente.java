package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Cliente;
import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class TestaContaCliente {
	
	
	public static void main(String[] args) {
		
		
		Cliente c1 = new Cliente();
		c1.setNome("Leonardo");
		c1.setEndereco("Rua Fulano 123");
		c1.setProfissao("Professor");
		
		Cliente c2 = new Cliente();
		c2.setNome("Gustavo");
		c2.setEndereco("Rua Fulano 123");
		c2.setProfissao("Professor");
		
		Conta conta = new Conta();
		conta.setId(2);
		
		c1.setConta(conta);
		c2.setConta(conta);
		
		
	    EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
	
		em.persist(c1);
//		em.persist(c2);
		
		em.getTransaction().commit();
		
		em.close();	
	}

}

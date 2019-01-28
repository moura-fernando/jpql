package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class TesteBuscaConta {
	
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		Conta conta = em.find(Conta.class, 1);
		conta.setTitular("João");
		
		System.out.println(conta.getTitular());
		
		em.getTransaction().commit();
		em.close();
		
		
	}

}

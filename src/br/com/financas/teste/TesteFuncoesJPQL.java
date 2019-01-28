package br.com.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAUtil;

public class TesteFuncoesJPQL {
	
	
	public static void main(String[] args) {
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta" +
				" and m.tipoMovimentacao = :pTipoMovimentacao" +
				" group by day(m.data), month(m.data), year(m.data)";
		
		
		Query query = em.createQuery(jpql); 
		query.setParameter("pConta", conta);
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.SAIDA  );
		
		//Uma alternativa seria utilizar um TypedQuerie, já indicando o tipo do retorno para evitar castException. O compilador já avisa o erro antes de rodar.
		//  TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		
		List<Double> soma =  (List<Double>) query.getResultList();
		
      
		System.out.println("A Media é: " + soma);
		
		em.getTransaction().commit();
		
		em.close();	
		
		
	}
}

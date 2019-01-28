package br.com.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.financas.dao.MovimentacaoDao;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAUtil;

public class TesteFuncoesJPQLNamedQuery {
	
	
	public static void main(String[] args) {
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
	    TypedQuery<Double> typedQuery = em.createNamedQuery("MediasPorDiaETipo", Double.class);
	    typedQuery.setParameter("pConta", conta);
	    typedQuery.setParameter("pTipo", TipoMovimentacao.SAIDA);

		List<Double> medias = typedQuery.getResultList();
      
		System.out.println("A Media é: " + medias);
		
		em.getTransaction().commit();
		
		em.close();	
		
		
	}
}

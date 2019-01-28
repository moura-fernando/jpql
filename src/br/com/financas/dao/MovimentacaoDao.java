package br.com.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.financas.modelo.Conta;
import br.com.financas.modelo.TipoMovimentacao;

public class MovimentacaoDao {
	
	
	private EntityManager em;
	
	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}	

	public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta) {

		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta" +
				" and m.tipoMovimentacao = :pTipoMovimentacao" +
				" group by day(m.data), month(m.data), year(m.data)";
		
		
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.SAIDA  );
		
		
		return query.getResultList();
	}
}

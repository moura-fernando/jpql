package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAUtil;

public class TesteJPQL {
	
	
	public static void main(String[] args) {
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		String jpql = "select m from Movimentacao m where m.conta = :pConta" +
				" and m.tipoMovimentacao = :pTipoMovimentacao" +
				" order by m.valor desc" ;
		
		
		Query query = em.createQuery(jpql); 
		query.setParameter("pConta", conta);
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.SAIDA  );
		
		List<Movimentacao> resultado = query.getResultList();
		
		resultado.forEach(m -> {
			System.out.println("---------");
			System.out.println(m.getDescricao());
			System.out.println(m.getValor());
			System.out.println(m.getTipoMovimentacao());
			System.out.println("-----------");
		});
		
//		for (Movimentacao movimentacao : resultado) {
//			System.out.println("---------");
//			System.out.println(movimentacao.getDescricao());
//			System.out.println(movimentacao.getValor());
//			System.out.println(movimentacao.getTipoMovimentacao());
//			System.out.println("-----------");
//		}
		
		em.getTransaction().commit();
		
		em.close();	
		
		
	}
}

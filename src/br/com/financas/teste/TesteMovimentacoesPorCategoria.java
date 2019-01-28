package br.com.financas.teste;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Categoria;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAUtil;

public class TesteMovimentacoesPorCategoria {

	
	public static void main(String[] args) {
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
		
		String jpql = "select m from Movimentacao m join m.categoria c where c = :pCategoria" +
				" order by m.valor desc" ;
		
		Query query = em.createQuery(jpql); 
		query.setParameter("pCategoria", categoria);
//		query.setParameter("pConta", conta);
//		query.setParameter("pTipoMovimentacao", TipoMovimentacao.SAIDA  );
		
		List<Movimentacao> resultado = query.getResultList();
		
		for (Movimentacao movimentacao : resultado) {
			System.out.println("---------");
			System.out.println(movimentacao.getDescricao());
			System.out.println(movimentacao.getValor());
			System.out.println(movimentacao.getTipoMovimentacao());
			System.out.println("-----------");
		}
		
		em.getTransaction().commit();
		
		em.close();	
		
		
	}
}

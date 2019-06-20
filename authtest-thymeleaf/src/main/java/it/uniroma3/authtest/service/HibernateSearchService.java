package it.uniroma3.authtest.service;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.authtest.model.Album;
import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.model.Fotografo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Component
public class HibernateSearchService {


	@Autowired
  private final EntityManagerFactory centityManagerF;


	@Autowired
	public HibernateSearchService(EntityManagerFactory entityManager) {
		super();
		this.centityManagerF = entityManager;
	}


	public void initializeHibernateSearch() throws InterruptedException {

		EntityManager em = centityManagerF.createEntityManager();
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		fullTextEntityManager.createIndexer().startAndWait();
	}

	@Transactional
	public List<Fotografia> fuzzySearchFotografia(String searchTerm) {
		EntityManager em = centityManagerF.createEntityManager();
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		em.getTransaction().begin();
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Fotografia.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(0).onFields("nome")
				.matching(searchTerm.concat("*")).createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Fotografia.class);

		// execute search

		@SuppressWarnings("unchecked")
		List<Fotografia> fotografiaList = jpaQuery.getResultList();


		em.getTransaction().commit();
		em.close();

		return fotografiaList;
	}


	@Transactional
	public List<Fotografo> fuzzySearchFotografo(String searchTerm) {
		EntityManager em = centityManagerF.createEntityManager();
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		em.getTransaction().begin();
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Fotografo.class).get();
		Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(0).onFields("nome", "cognome")
				.matching(searchTerm).createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Fotografo.class);

		// execute search

		List<Fotografo> fotografoList = jpaQuery.getResultList();


		em.getTransaction().commit();
		em.close();

		return fotografoList;
	}

	@Transactional
	public List<Album> fuzzySearchAlbum(String searchTerm) {
		EntityManager em = centityManagerF.createEntityManager();
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		em.getTransaction().begin();
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Album.class).get();
		Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(0).onFields("nome")
				.matching(searchTerm).createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Album.class);

		// execute search

		List<Album> albumList = jpaQuery.getResultList();


		em.getTransaction().commit();
		em.close();

		return albumList;
	}
}

package it.uniroma3.authtest.service;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.authtest.model.Album;
import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.model.Fotografo;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class HibernateSearchService {


	@Autowired
	private final EntityManager centityManager;


	@Autowired
	public HibernateSearchService(EntityManager entityManager) {
		super();
		this.centityManager = entityManager;
	}


	public void initializeHibernateSearch() {

		try {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public List<Fotografia> fuzzySearchFotografia(String searchTerm) {

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Fotografia.class).get();
		Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(1).onFields("nome")
				.matching(searchTerm).createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Fotografia.class);

		// execute search

		List<Fotografia> fotografiaList = null;
		try {
			fotografiaList = jpaQuery.getResultList();
		} catch (NoResultException nre) {
			;// do nothing

		}

		return fotografiaList;
	}


	@Transactional
	public List<Fotografo> fuzzySearchFotografo(String searchTerm) {

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Fotografia.class).get();
		Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(1).onFields("nome", "cognome")
				.matching(searchTerm).createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Fotografia.class);

		// execute search

		List<Fotografo> fotografoList = null;
		try {
			fotografoList = jpaQuery.getResultList();
		} catch (NoResultException nre) {
			;// do nothing

		}

		return fotografoList;
	}

	@Transactional
	public List<Album> fuzzySearchAlbum(String searchTerm) {

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Fotografia.class).get();
		Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(1).onFields("nome")
				.matching(searchTerm).createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Fotografia.class);

		// execute search

		List<Album> albumList = null;
		try {
			albumList = jpaQuery.getResultList();
		} catch (NoResultException nre) {
			;// do nothing

		}

		return albumList;
	}
}

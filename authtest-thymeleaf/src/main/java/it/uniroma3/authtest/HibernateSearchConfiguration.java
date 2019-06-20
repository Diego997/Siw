package it.uniroma3.authtest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.uniroma3.authtest.service.HibernateSearchService;

import javax.persistence.EntityManagerFactory;

@EnableAutoConfiguration
@Configuration
public class HibernateSearchConfiguration {

    @Autowired
    private EntityManagerFactory bentityManagerF;

    @Bean
    HibernateSearchService hibernateSearchService() throws InterruptedException {
        HibernateSearchService hibernateSearchService = new HibernateSearchService(bentityManagerF);
        hibernateSearchService.initializeHibernateSearch();
        return hibernateSearchService;
    }
}
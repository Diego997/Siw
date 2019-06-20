package it.uniroma3.authtest.storage;


import it.uniroma3.authtest.model.Richiesta;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RichiestaRepository extends JpaRepository<Richiesta, Long>{
	
	 Richiesta findByPrimaryKey(Long id);
  @Modifying
  @Transactional
  @Query( value="UPDATE Richiesta r SET r.checked = true WHERE r.primaryKey= :id")
  void setCheckedTrue(@Param("id") Long id);


	
}

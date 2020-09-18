package io.aperofy.dao.application;

import io.aperofy.entities.application.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    //List<VoyageEntity> findByAppartientA(String idFonctionnel);
    EventEntity findByEventCode(String eventCode);

    /*@Query("select o.idVoyage as idVoyage , o.nomVoyage as nomVoyage, o.codeBarre as codeBarre from  VoyageEntity o where o.appartientA=:x")
    List<VoyageEntity> recupereTousLesVoyageDeUser(@Param("x") String idFonctionnel);*/


}

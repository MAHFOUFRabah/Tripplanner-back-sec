package io.aperofy.dao.application;

import io.aperofy.entities.application.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    /*@Query("select o from  ArticleEntity o where o.voyageEntity.idVoyage=:x")
    List<ItemEntity> recupereTousLesArticleDuVoyageRepository(@Param("x") Long idVoyage);*/
}
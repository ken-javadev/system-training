package com.training.repository;

import com.training.model.TrainingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : Training.
 */
public interface TrainingJpaRepository extends PagingAndSortingRepository<TrainingEntity, Long> {
    @Query("select t from TrainingEntity t")
    Page<TrainingEntity> getAllData(Pageable pageable);

    @Query("select t from TrainingEntity t where t.id=:id")
    TrainingEntity getById(@Param("id") Long id);
}

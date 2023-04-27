package com.training.repository;

import com.training.model.KaryawanTrainingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : KaryawanTraining.
 */
public interface KaryawanTrainingJpaRepository extends PagingAndSortingRepository<KaryawanTrainingEntity, Long> {
    @Query("select t from KaryawanTrainingEntity t")
    Page<KaryawanTrainingEntity> getAllData(Pageable pageable);

    @Query("select t from KaryawanTrainingEntity t where t.id=:id")
    KaryawanTrainingEntity getById(@Param("id") Long id);
}

package com.training.repository;

import com.training.model.KaryawanEntity;
import com.training.model.RekeningEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : Rekening.
 */
public interface RekeningJpaRepository extends PagingAndSortingRepository<RekeningEntity, Long> {
    @Query("select t from RekeningEntity t")
    Page<RekeningEntity> getAllData(Pageable pageable);

    @Query("select t from RekeningEntity t where t.id=:id")
    RekeningEntity getById(@Param("id") Long id);

}

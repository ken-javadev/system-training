package com.training.repository;

import com.training.model.DetailKaryawanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : DetailKaryawan.
 */
public interface DetailKaryawanJpaRepository extends PagingAndSortingRepository<DetailKaryawanEntity, Long> {
    @Query("select t from DetailKaryawanEntity t")
    Page<DetailKaryawanEntity> getAllData(Pageable pageable);

    @Query("select t from DetailKaryawanEntity t where t.id=:id")
    DetailKaryawanEntity getById(@Param("id") Long id);

}

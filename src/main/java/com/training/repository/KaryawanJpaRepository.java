package com.training.repository;


import com.training.model.KaryawanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : Karyawan.
 */
public interface KaryawanJpaRepository extends PagingAndSortingRepository<KaryawanEntity, Long> {
    @Query("select t from KaryawanEntity t")
    Page<KaryawanEntity> getAllData(Pageable pageable);

    @Query("select t from KaryawanEntity t where t.id=:id")
    KaryawanEntity getById(@Param("id") Long id);
}

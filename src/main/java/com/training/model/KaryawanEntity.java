package com.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "karyawan", schema = "public")
// Define named queries here
@NamedQueries({
        @NamedQuery(name = "KaryawanEntity.countAll", query = "SELECT COUNT(x) FROM KaryawanEntity x")
})
public class KaryawanEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KARYAWAN_SEQ")
    @SequenceGenerator(name = "KARYAWAN_SEQ", sequenceName = "KARYAWAN_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name = "nama", nullable = false, length = 45)
    private String nama;

    @Column(name = "jk", nullable = false, length = 10)
    private String jk;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Column(name = "alamat")
    private String alamat;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "deleted_date")
    private Date deletedDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_date")
    private Date updatedDate;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @JsonIgnore
    @OneToMany(mappedBy = "karyawan", targetEntity = KaryawanTrainingEntity.class, fetch = FetchType.LAZY)
    private List<KaryawanTrainingEntity> listOfKaryawanTraining;

    @JsonIgnore
    @OneToMany(mappedBy = "karyawan", targetEntity = RekeningEntity.class, fetch = FetchType.LAZY)
    private List<RekeningEntity> listOfRekening;

    @OneToOne(mappedBy = "karyawan", targetEntity = DetailKaryawanEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DetailKaryawanEntity detailKaryawanEntity;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public KaryawanEntity() {
        super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : nama ( varchar ) 
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return this.nama;
    }

    //--- DATABASE MAPPING : jk ( varchar ) 
    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getJk() {
        return this.jk;
    }

    //--- DATABASE MAPPING : dob ( date ) 
    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDob() {
        return this.dob;
    }

    //--- DATABASE MAPPING : status ( varchar ) 
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    //--- DATABASE MAPPING : alamat ( text ) 
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return this.alamat;
    }

    //--- DATABASE MAPPING : created_date ( time ) 
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    //--- DATABASE MAPPING : deleted_date ( time ) 
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Date getDeletedDate() {
        return this.deletedDate;
    }

    //--- DATABASE MAPPING : updated_date ( time ) 
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate() {
        return this.updatedDate;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfKaryawanTraining(List<KaryawanTrainingEntity> listOfKaryawanTraining) {
        this.listOfKaryawanTraining = listOfKaryawanTraining;
    }

    public List<KaryawanTrainingEntity> getListOfKaryawanTraining() {
        return this.listOfKaryawanTraining;
    }

    public void setListOfRekening(List<RekeningEntity> listOfRekening) {
        this.listOfRekening = listOfRekening;
    }

    public List<RekeningEntity> getListOfRekening() {
        return this.listOfRekening;
    }

    public DetailKaryawanEntity getDetailKaryawanEntity() {
        return detailKaryawanEntity;
    }

    public void setDetailKaryawanEntity(DetailKaryawanEntity detailKaryawanEntity) {
        this.detailKaryawanEntity = detailKaryawanEntity;
    }



    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(id);
        sb.append("]:");
        sb.append(nama);
        sb.append("|");
        sb.append(jk);
        sb.append("|");
        sb.append(dob);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(alamat);
        sb.append("|");
        sb.append(createdDate);
        sb.append("|");
        sb.append(deletedDate);
        sb.append("|");
        sb.append(updatedDate);
        return sb.toString();
    }

}

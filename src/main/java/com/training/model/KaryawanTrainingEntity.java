package com.training.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="karyawan_training", schema="public" )
// Define named queries here
@NamedQueries( {
  @NamedQuery( name="KaryawanTrainingEntity.countAll", query="SELECT COUNT(x) FROM KaryawanTrainingEntity x" )
} )
public class KaryawanTrainingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KARYAWAN_TRAINING_SEQ")
    @SequenceGenerator(name = "KARYAWAN_TRAINING_SEQ", sequenceName = "KARYAWAN_TRAINING_SEQ", allocationSize = 1)
    @Column(name="id", nullable=false)
    private Long       id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Temporal(TemporalType.DATE)
    @Column(name="tanggal_training", nullable=false)
    private Date       tanggalTraining ;

    @Temporal(TemporalType.DATE)
    @Column(name="created_date")
    private Date       createdDate  ;

    @Temporal(TemporalType.DATE)
    @Column(name="deleted_date")
    private Date       deletedDate  ;

    @Temporal(TemporalType.DATE)
    @Column(name="updated_date")
    private Date       updatedDate  ;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_training", referencedColumnName="id")
    private TrainingEntity training    ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_karyawan", referencedColumnName="id")
    private KaryawanEntity karyawan    ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public KaryawanTrainingEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : tanggal_training ( date ) 
    public void setTanggalTraining( Date tanggalTraining ) {
        this.tanggalTraining = tanggalTraining;
    }
    public Date getTanggalTraining() {
        return this.tanggalTraining;
    }

    //--- DATABASE MAPPING : created_date ( timestamp ) 
    public void setCreatedDate( Date createdDate ) {
        this.createdDate = createdDate;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }

    //--- DATABASE MAPPING : deleted_date ( timestamp ) 
    public void setDeletedDate( Date deletedDate ) {
        this.deletedDate = deletedDate;
    }
    public Date getDeletedDate() {
        return this.deletedDate;
    }

    //--- DATABASE MAPPING : updated_date ( timestamp ) 
    public void setUpdatedDate( Date updatedDate ) {
        this.updatedDate = updatedDate;
    }
    public Date getUpdatedDate() {
        return this.updatedDate;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setTraining( TrainingEntity training ) {
        this.training = training;
    }
    public TrainingEntity getTraining() {
        return this.training;
    }

    public void setKaryawan( KaryawanEntity karyawan ) {
        this.karyawan = karyawan;
    }
    public KaryawanEntity getKaryawan() {
        return this.karyawan;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(tanggalTraining);
        sb.append("|");
        sb.append(createdDate);
        sb.append("|");
        sb.append(deletedDate);
        sb.append("|");
        sb.append(updatedDate);
        return sb.toString(); 
    } 

}

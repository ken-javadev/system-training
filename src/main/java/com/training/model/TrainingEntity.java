package com.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="training", schema="public" )
// Define named queries here
@NamedQueries( {
  @NamedQuery( name="TrainingEntity.countAll", query="SELECT COUNT(x) FROM TrainingEntity x" )
} )
public class TrainingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAINING_SEQ")
    @SequenceGenerator(name = "TRAINING_SEQ", sequenceName = "TRAINING_SEQ", allocationSize = 1)
    @Column(name="id", nullable=false)
    private Long       id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="tema", nullable=false)
    private String     tema         ;

    @Column(name="nama_pengajar", nullable=false, length=45)
    private String     namaPengajar ;

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
    @JsonIgnore
    @OneToMany(mappedBy="training", targetEntity=KaryawanTrainingEntity.class, fetch = FetchType.LAZY)
    private List<KaryawanTrainingEntity> listOfKaryawanTraining;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public TrainingEntity() {
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
    //--- DATABASE MAPPING : tema ( text ) 
    public void setTema( String tema ) {
        this.tema = tema;
    }
    public String getTema() {
        return this.tema;
    }

    //--- DATABASE MAPPING : nama_pengajar ( varchar ) 
    public void setNamaPengajar( String namaPengajar ) {
        this.namaPengajar = namaPengajar;
    }
    public String getNamaPengajar() {
        return this.namaPengajar;
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

    public void setListOfKaryawanTraining( List<KaryawanTrainingEntity> listOfKaryawanTraining ) {
        this.listOfKaryawanTraining = listOfKaryawanTraining;
    }
    public List<KaryawanTrainingEntity> getListOfKaryawanTraining() {
        return this.listOfKaryawanTraining;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(tema);
        sb.append("|");
        sb.append(namaPengajar);
        sb.append("|");
        sb.append(createdDate);
        sb.append("|");
        sb.append(deletedDate);
        sb.append("|");
        sb.append(updatedDate);
        return sb.toString(); 
    } 

}

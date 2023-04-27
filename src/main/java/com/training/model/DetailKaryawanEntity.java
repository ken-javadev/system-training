package com.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="detail_karyawan", schema="public" )
// Define named queries here
@NamedQueries( {
  @NamedQuery( name="DetailKaryawanEntity.countAll", query="SELECT COUNT(x) FROM DetailKaryawanEntity x" )
} )
public class DetailKaryawanEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETAILKARYAWAN_SEQ")
    @SequenceGenerator(name = "DETAILKARYAWAN_SEQ", sequenceName = "DETAILKARYAWAN_SEQ", allocationSize = 1)
    @Column(name="id")
    private Long       id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="nik", nullable=false, length=20)
    private String     nik          ;

    @Column(name="npwp", length=20)
    private String     npwp         ;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_karyawan", referencedColumnName="id")
    private KaryawanEntity karyawan    ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public DetailKaryawanEntity() {
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
    //--- DATABASE MAPPING : nik ( varchar ) 
    public void setNik( String nik ) {
        this.nik = nik;
    }
    public String getNik() {
        return this.nik;
    }

    //--- DATABASE MAPPING : npwp ( varchar ) 
    public void setNpwp( String npwp ) {
        this.npwp = npwp;
    }
    public String getNpwp() {
        return this.npwp;
    }

    //--- DATABASE MAPPING : created_date ( time ) 
    public void setCreatedDate( Date createdDate ) {
        this.createdDate = createdDate;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }

    //--- DATABASE MAPPING : deleted_date ( time ) 
    public void setDeletedDate( Date deletedDate ) {
        this.deletedDate = deletedDate;
    }
    public Date getDeletedDate() {
        return this.deletedDate;
    }

    //--- DATABASE MAPPING : updated_date ( time ) 
    public void setUpdatedDate( Date updatedDate ) {
        this.updatedDate = updatedDate;
    }
    public Date getUpdatedDate() {
        return this.updatedDate;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
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
        sb.append(nik);
        sb.append("|");
        sb.append(npwp);
        sb.append("|");
        sb.append(createdDate);
        sb.append("|");
        sb.append(deletedDate);
        sb.append("|");
        sb.append(updatedDate);
        return sb.toString(); 
    } 

}

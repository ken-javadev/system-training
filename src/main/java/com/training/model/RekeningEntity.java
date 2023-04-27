package com.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="rekening", schema="public" )
// Define named queries here
@NamedQueries( {
  @NamedQuery( name="RekeningEntity.countAll", query="SELECT COUNT(x) FROM RekeningEntity x" )
} )
public class RekeningEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REKENING_SEQ")
    @SequenceGenerator(name = "REKENING_SEQ", sequenceName = "REKENING_SEQ", allocationSize = 1)
    @Column(name="id", nullable=false)
    private Long       id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="nama", nullable=false, length=45)
    private String     nama         ;

    @Column(name="jenis", nullable=false, length=10)
    private String     jenis        ;

    @Column(name="nomor", nullable=false, length=20)
    private String     nomor        ;

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
    @JoinColumn(name="id_karyawan", referencedColumnName="id")
    private KaryawanEntity karyawan    ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public RekeningEntity() {
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
    //--- DATABASE MAPPING : nama ( varchar ) 
    public void setNama( String nama ) {
        this.nama = nama;
    }
    public String getNama() {
        return this.nama;
    }

    //--- DATABASE MAPPING : jenis ( varchar ) 
    public void setJenis( String jenis ) {
        this.jenis = jenis;
    }
    public String getJenis() {
        return this.jenis;
    }

    //--- DATABASE MAPPING : nomor ( varchar ) 
    public void setNomor( String nomor ) {
        this.nomor = nomor;
    }
    public String getNomor() {
        return this.nomor;
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
        sb.append(nama);
        sb.append("|");
        sb.append(jenis);
        sb.append("|");
        sb.append(nomor);
        sb.append("|");
        sb.append(createdDate);
        sb.append("|");
        sb.append(deletedDate);
        sb.append("|");
        sb.append(updatedDate);
        return sb.toString(); 
    } 

}

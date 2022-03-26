/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.database;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author habtamu
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByFirtstname", query = "SELECT u FROM Users u WHERE u.firtstname = :firtstname"),
    @NamedQuery(name = "Users.findByMiddlename", query = "SELECT u FROM Users u WHERE u.middlename = :middlename"),
    @NamedQuery(name = "Users.findByLastname", query = "SELECT u FROM Users u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPhoneNumber", query = "SELECT u FROM Users u WHERE u.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Users.findByHousePhoneNumber", query = "SELECT u FROM Users u WHERE u.housePhoneNumber = :housePhoneNumber"),
    @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status"),
    @NamedQuery(name = "Users.findByKebele", query = "SELECT u FROM Users u WHERE u.kebele = :kebele"),
    @NamedQuery(name = "Users.findByIdNumber", query = "SELECT u FROM Users u WHERE u.idNumber = :idNumber"),
    @NamedQuery(name = "Users.findByHouseNumber", query = "SELECT u FROM Users u WHERE u.houseNumber = :houseNumber"),
    @NamedQuery(name = "Users.findByPobox", query = "SELECT u FROM Users u WHERE u.pobox = :pobox"),
    @NamedQuery(name = "Users.findByDateOfBirth", query = "SELECT u FROM Users u WHERE u.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Users.findBySex", query = "SELECT u FROM Users u WHERE u.sex = :sex"),
    @NamedQuery(name = "Users.findByConfirmpassword", query = "SELECT u FROM Users u WHERE u.confirmpassword = :confirmpassword"),
    @NamedQuery(name = "Users.findByPrevilege", query = "SELECT u FROM Users u WHERE u.previlege = :previlege"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findBySample", query = "SELECT u FROM Users u WHERE u.sample = :sample"),
    @NamedQuery(name = "Users.findByCasedetail", query = "SELECT u FROM Users u WHERE u.casedetail = :casedetail"),
    @NamedQuery(name = "Users.findByCasestartdate", query = "SELECT u FROM Users u WHERE u.casestartdate = :casestartdate")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2000)
    @Column(name = "firtstname")
    private String firtstname;
    @Size(max = 2000)
    @Column(name = "middlename")
    private String middlename;
    @Size(max = 2000)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 2000)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2000)
    @Column(name = "email")
    private String email;
    @Size(max = 50)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(max = 50)
    @Column(name = "house_phone_number")
    private String housePhoneNumber;
    @Size(max = 20)
    @Column(name = "status")
    private String status;
    @Size(max = 200)
    @Column(name = "kebele")
    private String kebele;
    @Size(max = 200)
    @Column(name = "id_number")
    private String idNumber;
    @Size(max = 200)
    @Column(name = "house_number")
    private String houseNumber;
    @Size(max = 200)
    @Column(name = "pobox")
    private String pobox;
    @Size(max = 200)
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Size(max = 200)
    @Column(name = "sex")
    private String sex; 
    @Size(max = 2000)
    @Column(name = "confirmpassword")
    private String confirmpassword;
    @Size(max = 2000)
    @Column(name = "previlege")
    private String previlege;
    @Size(max = 2000)
    @Column(name = "username")
    private String username;
    @Size(max = 100)
    @Column(name = "sample")
    private String sample;
    @Size(max = 2147483647)
    @Column(name = "casedetail")
    private String casedetail;
    @Column(name = "casestartdate")
    @Temporal(TemporalType.DATE)
    private Date casestartdate;
    @JoinColumn(name = "nationality", referencedColumnName = "id")
    @ManyToOne
    private Nationalities nationality;
    @JoinColumn(name = "organization_type", referencedColumnName = "id")
    @ManyToOne
    private Officetypes organizationType;
    @JoinColumn(name = "region", referencedColumnName = "id")
    @ManyToOne
    private Regions region;
    @JoinColumn(name = "title", referencedColumnName = "id")
    @ManyToOne
    private Titlerank title;
    @JoinColumn(name = "woreda", referencedColumnName = "id")
    @ManyToOne
    private Weredas woreda;
    @JoinColumn(name = "zone", referencedColumnName = "id")
    @ManyToOne
    private Zones zone; 
     
    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirtstname() {
        return firtstname;
    }

    public void setFirtstname(String firtstname) {
        this.firtstname = firtstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        
        return password;
    }

    public void setPassword(String password) {
        String pwd = JavaUtilsEncryption.generateSaltedHash(password.trim(), password.trim());
        this.password = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHousePhoneNumber() {
        return housePhoneNumber;
    }

    public void setHousePhoneNumber(String housePhoneNumber) {
        this.housePhoneNumber = housePhoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKebele() {
        return kebele;
    }

    public void setKebele(String kebele) {
        this.kebele = kebele;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPobox() {
        return pobox;
    }

    public void setPobox(String pobox) {
        this.pobox = pobox;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    } 
    
    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getPrevilege() {
        return previlege;
    }

    public void setPrevilege(String previlege) {
        this.previlege = previlege;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getCasedetail() {
        return casedetail;
    }

    public void setCasedetail(String casedetail) {
        this.casedetail = casedetail;
    }

    public Date getCasestartdate() {
        return casestartdate;
    }

    public void setCasestartdate(Date casestartdate) {
        this.casestartdate = casestartdate;
    }

    public Nationalities getNationality() {
        return nationality;
    }

    public void setNationality(Nationalities nationality) {
        this.nationality = nationality;
    }

    public Officetypes getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(Officetypes organizationType) {
        this.organizationType = organizationType;
    }

    public Regions getRegion() {
        return region;
    }

    public void setRegion(Regions region) {
        this.region = region;
    }

    public Titlerank getTitle() {
        return title;
    }

    public void setTitle(Titlerank title) {
        this.title = title;
    }

    public Weredas getWoreda() {
        return woreda;
    }

    public void setWoreda(Weredas woreda) {
        this.woreda = woreda;
    }

    public Zones getZone() {
        return zone;
    }

    public void setZone(Zones zone) {
        this.zone = zone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.database.Users[ id=" + id + " ]";
    }
    
}

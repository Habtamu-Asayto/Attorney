/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "attorney_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AttorneyAccount.findAll", query = "SELECT a FROM AttorneyAccount a"),
    @NamedQuery(name = "AttorneyAccount.findById", query = "SELECT a FROM AttorneyAccount a WHERE a.id = :id"),
    @NamedQuery(name = "AttorneyAccount.findByName", query = "SELECT a FROM AttorneyAccount a WHERE a.name = :name"),
    @NamedQuery(name = "AttorneyAccount.findByMiddleName", query = "SELECT a FROM AttorneyAccount a WHERE a.middleName = :middleName"),
    @NamedQuery(name = "AttorneyAccount.findByLastName", query = "SELECT a FROM AttorneyAccount a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "AttorneyAccount.findBySex", query = "SELECT a FROM AttorneyAccount a WHERE a.sex = :sex"),
    @NamedQuery(name = "AttorneyAccount.findByPhone", query = "SELECT a FROM AttorneyAccount a WHERE a.phone = :phone"),
    @NamedQuery(name = "AttorneyAccount.findByOfficePhone", query = "SELECT a FROM AttorneyAccount a WHERE a.officePhone = :officePhone"),
    @NamedQuery(name = "AttorneyAccount.findByEmail", query = "SELECT a FROM AttorneyAccount a WHERE a.email = :email"),
    @NamedQuery(name = "AttorneyAccount.findByDataOfGraduation", query = "SELECT a FROM AttorneyAccount a WHERE a.dataOfGraduation = :dataOfGraduation"),
    @NamedQuery(name = "AttorneyAccount.findByAcadamicRank", query = "SELECT a FROM AttorneyAccount a WHERE a.acadamicRank = :acadamicRank"),
    @NamedQuery(name = "AttorneyAccount.findByPhoto", query = "SELECT a FROM AttorneyAccount a WHERE a.photo = :photo"),
    @NamedQuery(name = "AttorneyAccount.findByBiography", query = "SELECT a FROM AttorneyAccount a WHERE a.biography = :biography"),
    @NamedQuery(name = "AttorneyAccount.findByKebele", query = "SELECT a FROM AttorneyAccount a WHERE a.kebele = :kebele"),
    @NamedQuery(name = "AttorneyAccount.findByWorkingPlace", query = "SELECT a FROM AttorneyAccount a WHERE a.workingPlace = :workingPlace"),
    @NamedQuery(name = "AttorneyAccount.findByUsername", query = "SELECT a FROM AttorneyAccount a WHERE a.username = :username"),
    @NamedQuery(name = "AttorneyAccount.findByPassword", query = "SELECT a FROM AttorneyAccount a WHERE a.password = :password")})
public class AttorneyAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1000)
    @Column(name = "name")
    private String name;
    @Size(max = 1000)
    @Column(name = "middle_name")
    private String middleName;
    @Size(max = 1000)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 100)
    @Column(name = "sex")
    private String sex;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "phone")
    private String phone;
    @Size(max = 100)
    @Column(name = "office_phone")
    private String officePhone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 1000)
    @Column(name = "email")
    private String email;
    @Column(name = "data_of_graduation")
    @Temporal(TemporalType.DATE)
    private Date dataOfGraduation;
    @Size(max = 1000)
    @Column(name = "acadamic_rank")
    private String acadamicRank;
    @Size(max = 1000)
    @Column(name = "photo")
    private String photo;
    @Size(max = 2147483647)
    @Column(name = "biography")
    private String biography;
    @Size(max = 100)
    @Column(name = "kebele")
    private String kebele;
    @Size(max = 1000)
    @Column(name = "working_place")
    private String workingPlace;
    @Size(max = 1000)
    @Column(name = "username")
    private String username;
    @Size(max = 1000)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "region", referencedColumnName = "id")
    @ManyToOne
    private Regions region;
    @JoinColumn(name = "woreda", referencedColumnName = "id")
    @ManyToOne
    private Weredas woreda;
    @JoinColumn(name = "zone", referencedColumnName = "id")
    @ManyToOne
    private Zones zone;

    public AttorneyAccount() {
    }

    public AttorneyAccount(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataOfGraduation() {
        return dataOfGraduation;
    }

    public void setDataOfGraduation(Date dataOfGraduation) {
        this.dataOfGraduation = dataOfGraduation;
    }

    public String getAcadamicRank() {
        return acadamicRank;
    }

    public void setAcadamicRank(String acadamicRank) {
        this.acadamicRank = acadamicRank;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getKebele() {
        return kebele;
    }

    public void setKebele(String kebele) {
        this.kebele = kebele;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
         String pwd = JavaUtilsEncryption.generateSaltedHash(password.trim(), password.trim());
         this.password = pwd;
    }

    public Regions getRegion() {
        return region;
    }

    public void setRegion(Regions region) {
        this.region = region;
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
        if (!(object instanceof AttorneyAccount)) {
            return false;
        }
        AttorneyAccount other = (AttorneyAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.database.AttorneyAccount[ id=" + id + " ]";
    }
    
}

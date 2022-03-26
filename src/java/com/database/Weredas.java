/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.database;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author habtamu
 */
@Entity
@Table(name = "weredas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Weredas.findAll", query = "SELECT w FROM Weredas w"),
    @NamedQuery(name = "Weredas.findById", query = "SELECT w FROM Weredas w WHERE w.id = :id"),
    @NamedQuery(name = "Weredas.findByWeredaname", query = "SELECT w FROM Weredas w WHERE w.weredaname = :weredaname")})
public class Weredas implements Serializable {

    @Size(max = 200)
    @Column(name = "weredaname")
    private String weredaname;
    @OneToMany(mappedBy = "woreda")
    private Collection<Users> usersCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "zoneid", referencedColumnName = "id")
    @ManyToOne
    private Zones zoneid;
    @OneToMany(mappedBy = "woreda")
    private Collection<AttorneyAccount> attorneyAccountCollection;

    public Weredas() {
    }

    public Weredas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Zones getZoneid() {
        return zoneid;
    }

    public void setZoneid(Zones zoneid) {
        this.zoneid = zoneid;
    }

    @XmlTransient
    public Collection<AttorneyAccount> getAttorneyAccountCollection() {
        return attorneyAccountCollection;
    }

    public void setAttorneyAccountCollection(Collection<AttorneyAccount> attorneyAccountCollection) {
        this.attorneyAccountCollection = attorneyAccountCollection;
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
        if (!(object instanceof Weredas)) {
            return false;
        }
        Weredas other = (Weredas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return weredaname;
    }

    public String getWeredaname() {
        return weredaname;
    }

    public void setWeredaname(String weredaname) {
        this.weredaname = weredaname;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }
    
}

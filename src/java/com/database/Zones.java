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
@Table(name = "zones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zones.findAll", query = "SELECT z FROM Zones z"),
    @NamedQuery(name = "Zones.findById", query = "SELECT z FROM Zones z WHERE z.id = :id"),
    @NamedQuery(name = "Zones.findByZonename", query = "SELECT z FROM Zones z WHERE z.zonename = :zonename")})
public class Zones implements Serializable {

    @Size(max = 200)
    @Column(name = "zonename")
    private String zonename;
    @OneToMany(mappedBy = "zone")
    private Collection<Users> usersCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "zoneid")
    private Collection<Weredas> weredasCollection;
    @OneToMany(mappedBy = "zone")
    private Collection<AttorneyAccount> attorneyAccountCollection;
    @JoinColumn(name = "regionid", referencedColumnName = "id")
    @ManyToOne
    private Regions regionid;

    public Zones() {
    }

    public Zones(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @XmlTransient
    public Collection<Weredas> getWeredasCollection() {
        return weredasCollection;
    }

    public void setWeredasCollection(Collection<Weredas> weredasCollection) {
        this.weredasCollection = weredasCollection;
    }

    @XmlTransient
    public Collection<AttorneyAccount> getAttorneyAccountCollection() {
        return attorneyAccountCollection;
    }

    public void setAttorneyAccountCollection(Collection<AttorneyAccount> attorneyAccountCollection) {
        this.attorneyAccountCollection = attorneyAccountCollection;
    }

    public Regions getRegionid() {
        return regionid;
    }

    public void setRegionid(Regions regionid) {
        this.regionid = regionid;
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
        if (!(object instanceof Zones)) {
            return false;
        }
        Zones other = (Zones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return zonename;
    }

    public String getZonename() {
        return zonename;
    }

    public void setZonename(String zonename) {
        this.zonename = zonename;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }
    
}

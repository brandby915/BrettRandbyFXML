/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Brett Randby
 */
@Entity
@Table(name = "DAILYHEALTHMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dailyhealthmodel.findAll", query = "SELECT d FROM Dailyhealthmodel d")
    , @NamedQuery(name = "Dailyhealthmodel.findByDailyhealthid", query = "SELECT d FROM Dailyhealthmodel d WHERE d.dailyhealthid = :dailyhealthid")
    , @NamedQuery(name = "Dailyhealthmodel.findByCarbohydrates", query = "SELECT d FROM Dailyhealthmodel d WHERE d.carbohydrates = :carbohydrates")
    , @NamedQuery(name = "Dailyhealthmodel.findByFat", query = "SELECT d FROM Dailyhealthmodel d WHERE d.fat = :fat")
    , @NamedQuery(name = "Dailyhealthmodel.findByProtein", query = "SELECT d FROM Dailyhealthmodel d WHERE d.protein = :protein")
    , @NamedQuery(name = "Dailyhealthmodel.findByMood", query = "SELECT d FROM Dailyhealthmodel d WHERE d.mood = :mood")
    , @NamedQuery(name = "Dailyhealthmodel.findByJournalentry", query = "SELECT d FROM Dailyhealthmodel d WHERE d.journalentry = :journalentry")})
    //, @NamedQuery(name = "Dailyhealthmodel.findByIDAndFat", query = "SELECT d FROM Dailyhealthmodel d WHERE d.id = :id and d.fat = :fat")
    //, @NamedQuery(name = "Dailyhealthmodel.findByMoodAndFat", query = "SELECT d FROM Dailyhealthmodel d WHERE d.mood = :mood and d.fat = :fat")
public class Dailyhealthmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DAILYHEALTHID")
    private Integer dailyhealthid;
    @Column(name = "CARBOHYDRATES")
    private Integer carbohydrates;
    @Column(name = "FAT")
    private Integer fat;
    @Column(name = "PROTEIN")
    private Integer protein;
    @Column(name = "MOOD")
    private String mood;
    @Column(name = "JOURNALENTRY")
    private String journalentry;

    public Dailyhealthmodel() {
    }

    public Dailyhealthmodel(Integer dailyhealthid) {
        this.dailyhealthid = dailyhealthid;
    }

    public Integer getDailyhealthid() {
        return dailyhealthid;
    }

    public void setDailyhealthid(Integer dailyhealthid) {
        this.dailyhealthid = dailyhealthid;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getJournalentry() {
        return journalentry;
    }

    public void setJournalentry(String journalentry) {
        this.journalentry = journalentry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dailyhealthid != null ? dailyhealthid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dailyhealthmodel)) {
            return false;
        }
        Dailyhealthmodel other = (Dailyhealthmodel) object;
        if ((this.dailyhealthid == null && other.dailyhealthid != null) || (this.dailyhealthid != null && !this.dailyhealthid.equals(other.dailyhealthid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Dailyhealthmodel[ dailyhealthid=" + dailyhealthid + " ]";
    }
}
   
    

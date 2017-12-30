package com.fabio.springmvc.domain;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class AbstractDomainClass implements DomainObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;

    private Date dateCreated;
    private Date lastUpdated;

    @PreUpdate
    @PrePersist
    public void updateTimeStamps(){
        lastUpdated = new Date();
        if(dateCreated == null){
            dateCreated = new Date();
        }
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }
}

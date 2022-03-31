package com.example.bootcamp.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(EntityListeners.class)
public class Auditinginfo implements Serializable {
    @CreatedDate
    private Date dateCreated;
    @LastModifiedDate
    private Date lastUpdated;
    private String createdBy;
    private String updatedBy;

}

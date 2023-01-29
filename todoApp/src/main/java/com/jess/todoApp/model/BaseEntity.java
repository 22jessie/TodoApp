/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jess.todoApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 *
 * @author jessica-22
 */
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable=false)
    @JsonIgnore
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(insertable=false)
    @JsonIgnore
    private LocalDateTime updatedAt;
}

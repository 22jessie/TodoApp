/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jess.todoApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jessica-22
 */


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task extends BaseEntity implements Serializable {
    
    @Column(name="task_id")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="native")
    @GenericGenerator(name="native",strategy="native")
    private long id;
    
    private boolean completed;
    
    
    @ManyToOne(fetch=FetchType.LAZY,optional=true)
    @JoinColumn(name="category_id_fk",referencedColumnName="category_id")
    @JsonIgnore
    private Category category;
    
    
    @NotBlank(message="Description cannot be blank")
    @Size(max=200, message="Description cannot exceed 200 characters.")
    private String description;

    
    Task(String description, boolean completed, Category c) {
        this.description=description;
        this.completed=completed;
        category=c;
    }
    

    public boolean getCompleted() {
        return completed;
    }
    
}

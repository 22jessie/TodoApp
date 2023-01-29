/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jess.todoApp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="native")
    @GenericGenerator(name="native",strategy="native")
    @Column(name="category_id")
    private long id;
    
    @NotBlank
    @Size(max=20, message="Category can't exceed 20 characters")
    private String name;
   
    @OneToMany(mappedBy="category", fetch=FetchType.EAGER, cascade = CascadeType.ALL,targetEntity = Task.class)
    private List<Task> tasks=new ArrayList<>();
    
    
    public boolean removeTask(Task t) {
         return tasks.remove(t);
    }

    public boolean addTask(Task t) {
        return tasks.add(t);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Category){
            Category c = (Category)o;
            return c.getId() == getId();
        }
        return false;
    }

    
}

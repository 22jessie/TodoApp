/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jess.todoApp.repositories;

import com.jess.todoApp.model.Category;
import com.jess.todoApp.model.Task;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jessica-22
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

    public Optional<Category> getTodoById(long id);
    
    
    
}

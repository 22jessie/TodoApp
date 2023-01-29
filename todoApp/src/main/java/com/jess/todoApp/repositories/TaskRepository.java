/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jess.todoApp.repositories;

import com.jess.todoApp.model.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jessica-22
 */

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    public Optional<Task> getTaskById(long id);
    
    public List<Task> findTasksByCategoryId(long categoryId);
}

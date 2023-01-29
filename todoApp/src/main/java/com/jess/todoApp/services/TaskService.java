/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jess.todoApp.services;

import com.jess.todoApp.model.Task;
import com.jess.todoApp.repositories.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jessica-22
 */

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepo;

    public Task save(Task t) {
        return taskRepo.saveAndFlush(t);
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepo.getTaskById(id);
    }

    public void remove(Task t) {
        taskRepo.delete(t);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public List<Task> getTasksWithCategoryId(long categoryId) {
        return taskRepo.findTasksByCategoryId(categoryId);
    }
    
    
}

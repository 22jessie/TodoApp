/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jess.todoApp.services;

import com.jess.todoApp.model.Category;
import com.jess.todoApp.model.Task;
import com.jess.todoApp.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jessica-22
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepo;

    public Optional<Category> getCategoryById(long id) {
        return categoryRepo.getTodoById(id);
    }
    
    public boolean existsById(long id){
        return categoryRepo.existsById(id);
    }

    public Category save(Category c) {
        return categoryRepo.saveAndFlush(c);
    }

    public void delete(Category c) {
        categoryRepo.delete(c);
    }

    public List<Category> getAll() {
        return categoryRepo.findAll();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jess.todoApp.controllers;


import com.jess.todoApp.model.Category;
import com.jess.todoApp.model.GenericResponse;
import com.jess.todoApp.model.Task;
import com.jess.todoApp.services.CategoryService;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author jessica-22
 */

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping(path="/api/categories", produces={
    MediaType.APPLICATION_JSON_VALUE
    
})
public class CategoryController {
    
    @Autowired
    private CategoryService categoryServ;
    
    @PostMapping("/") //  add new category
    public ResponseEntity<Task> createCategory(@Valid @RequestBody Category c){
        GenericResponse gr;
        
        c=categoryServ.save(c);
        gr=new GenericResponse(c.getId(),"Category created successfully.");
        return new ResponseEntity(gr,HttpStatus.CREATED);
    }
    
    @GetMapping("/")
    public List<Category> getAllCategories(){
        return categoryServ.getAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Task> getCategory(@PathVariable(name="id")long id){
        Optional<Category> opt=categoryServ.getCategoryById(id);
        if(opt.isPresent()){
            return new ResponseEntity(opt.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity("The ID received is invalid",HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateCategoryName(@PathVariable(name="id")long id, @Valid @RequestBody Category nCat){
        Optional<Category> opt=categoryServ.getCategoryById(id);
        Category prev;
        if(opt.isPresent()){
            prev=opt.get();
            prev.setName(nCat.getName());
            categoryServ.save(prev);
            return new ResponseEntity("Category updated successfully!",HttpStatus.OK);
        }else{
            return new ResponseEntity("The ID received is invalid", HttpStatus.BAD_REQUEST);
        } 
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable(name="id") long id){
        Optional<Category> opt=categoryServ.getCategoryById(id);
        if(opt.isPresent()){
            categoryServ.delete(opt.get());
            return new ResponseEntity("Category deleted successfully!",HttpStatus.OK);
        }else{
            return new ResponseEntity("The ID received is invalid", HttpStatus.BAD_REQUEST);
        }
    }
   
    
}

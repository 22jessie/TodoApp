/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jess.todoApp.controllers;

import com.jess.todoApp.model.Category;
import com.jess.todoApp.model.Task;
import com.jess.todoApp.model.GenericResponse;
import com.jess.todoApp.services.CategoryService;
import com.jess.todoApp.services.TaskService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jessica-22
 */

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping(path="/api/tasks", produces={
    MediaType.APPLICATION_JSON_VALUE
    
})
public class TaskController {
    
    @Autowired
    private TaskService taskServ;
    
    @Autowired
    private CategoryService categoryServ;
    
  
    
    
    @GetMapping("/")
    public ResponseEntity<?> getTasksByCategory(
            @RequestParam(required=false,value="categoryId")Long categoryId){
        List<Task> tasks;
        
        if(categoryId != null) {
            if(categoryServ.existsById(categoryId)){
                tasks=taskServ.getTasksWithCategoryId(categoryId);
                return new ResponseEntity<>(tasks,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("The ID for category is not valid",HttpStatus.BAD_REQUEST);
            }
        }else{
            tasks=taskServ.getAllTasks();
            return new ResponseEntity<>(tasks,HttpStatus.OK);
        }
        
        
    }
    
    @PostMapping("/")
    public ResponseEntity<?> createTask(@Valid @RequestBody Task task,
            @RequestParam(required=false,value="categoryId")Long categoryId){
        Optional<Category> category;
        GenericResponse gr;
        
        if(categoryId!=null){
            category=categoryServ.getCategoryById(categoryId);
            if(category.isPresent()){
                task.setCategory(category.get());
                task=taskServ.save(task);
                gr=new GenericResponse(task.getId(),"Task created successfully!");
                return new ResponseEntity(gr,HttpStatus.OK);
            }else{
                return new ResponseEntity("The ID for category is not valid",HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity("Missing ID for category in params",HttpStatus.BAD_REQUEST);
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable(name="id")long id, @Valid @RequestBody Task receivedTask){ 
        Optional<Task> optTask;
        Task prevTask;
        optTask=taskServ.getTaskById(id);
        if(optTask.isPresent()){
            prevTask=optTask.get();
            prevTask.setCompleted(receivedTask.getCompleted());
            prevTask.setDescription(receivedTask.getDescription());
            taskServ.save(prevTask);
            return new ResponseEntity("Task updated successfully!",HttpStatus.OK);
        }
        return new ResponseEntity("The ID for category is not valid",HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable(name="id")long id){
        Optional<Task> optTask;
         
        optTask=taskServ.getTaskById(id);
        System.out.println("El id recibido es " + id);
        if(optTask.isPresent()){
           taskServ.remove(optTask.get());
            return new ResponseEntity("Task deleted successfully!",HttpStatus.OK);
        }
        return new ResponseEntity("The task ID is not valid",HttpStatus.BAD_REQUEST);
    }

}

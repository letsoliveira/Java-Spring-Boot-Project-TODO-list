package com.avanade.todo.service;

import com.avanade.todo.exception.InvalidInputException;
import com.avanade.todo.exception.ResourceNotFoundException;
import com.avanade.todo.model.Task;
import com.avanade.todo.model.dto.Activity;
import com.avanade.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;
    @Autowired
    private BoredApiService boredApiService;
    public Task create(Task task){
        task.setCreatedAt(LocalDateTime.now());
        task.setIsCompleted(false);
        task.setCompletedAt(null);
        return this.repository.save(task);
    }
    public List<Task> findAll(){
        return repository.findAll();
    }
    public Task findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found ID:" + id));
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
    public Task update (Task task){
        if(task.getId()==null)
            throw new InvalidInputException("There is no ID");
        return repository.save(task);
    }
    public Task generateRandom(){
        Activity activity = boredApiService.callBoredApi();
        Task task = new Task();
        task.setTitle(activity.getActivity());
        task.setDescription(activity.getType() + "Task");
        return create(task);
    }
}

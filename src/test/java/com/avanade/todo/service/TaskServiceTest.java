package com.avanade.todo.service;

import com.avanade.todo.model.Task;
import com.avanade.todo.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    List<Task> taskList;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        taskList = new ArrayList<>();
        Task task1 = new Task (1L, "Configurar Mockito", "Configurar os mocks para teste unitário", false, LocalDateTime.now(), null);
        Task task2 = new Task(2L, "Criar testes unitários", "Configurar chamadas de teste unitário", false, LocalDateTime.now(),null);
        taskList.add(task1);
        taskList.add(task2);
    }

    @Test
    void create() {
        Task task = new Task (1L, "Configurar Mockito", "Configurar os mocks para teste unitário", true, LocalDateTime.now(), LocalDateTime.now());
        Task expected = new Task (1L, "Configurar Mockito", "Configurar os mocks para teste unitário", false, LocalDateTime.now(), null);
        when(repository.save(task)).thenReturn(task);
        Task response = service.create(task);
        Assertions.assertEquals(expected.getIsCompleted(),response.getIsCompleted());
        verify(repository,times(1)).save(any());
    }

    @Test
    void findAll() {
        when (repository.findAll()).thenReturn(taskList);
        List<Task> tasks = service.findAll();
        Assertions.assertEquals(tasks,taskList);
        verify(repository,times(1)).findAll();
    }

    @Test
    void findById() {
        when (repository.findById(any())).thenReturn(Optional.ofNullable(taskList.get(0)));
        Task task = service.findById(1L);
        Assertions.assertEquals(task,taskList.get(0));
        verify(repository,times(1)).findById(any());
    }

    @Test
    void delete() {
        doNothing().when(repository).deleteById(any());
        service.delete(1L);
        verify(repository,times(1)).deleteById(any());
    }

    @Test
    void update() {
        Task task = taskList.get(0);
        task.setIsCompleted(true);
        task.setCompletedAt(LocalDateTime.now());
        when(repository.save(task)).thenReturn(task);
        Task response=service.update(task);
        Assertions.assertEquals(task.getIsCompleted(),response.getIsCompleted());
        verify(repository,times(1)).save(any());
    }

    }

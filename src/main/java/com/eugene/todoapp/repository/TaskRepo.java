package com.eugene.todoapp.repository;

import com.eugene.todoapp.domain.Task;
import com.eugene.todoapp.domain.TaskStatus;
import javafx.scene.control.Tab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findAllByStatus(TaskStatus s);
}

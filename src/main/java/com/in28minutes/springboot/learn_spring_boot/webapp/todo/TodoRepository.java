package com.in28minutes.springboot.learn_spring_boot.webapp.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    public java.util.List<Todo> findByUsername(String username);

}

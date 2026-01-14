package com.in28minutes.springboot.learn_spring_boot.webapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("username")
public class TodoControllerJpa {

    
    private final TodoRepository todoRepository;

    public TodoControllerJpa(final TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap) {
        final String username = getLoggedInUsername();
        List<Todo> todos = todoRepository.findByUsername(username);
        modelMap.addAttribute("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(final ModelMap modelMap) {
        final Todo todo = new Todo(0, getLoggedInUsername(), "", LocalDate.now().plusYears(1), false);
        modelMap.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(@Valid final Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        todoRepository.save(new Todo(0, getLoggedInUsername(), todo.getDescription(), todo.getTargetDate(), false));
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(final ModelMap modelMap, @RequestParam int id) {
        final Optional<Todo> todoOpt = todoRepository.findById(id);
        final Todo todo = todoOpt.get();
        modelMap.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(final ModelMap modelMap, @Valid final Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        todo.setUsername(getLoggedInUsername());
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    private String getLoggedInUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

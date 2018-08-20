package com.example.demo.controllers;

import com.example.demo.models.Project;
import com.example.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping(value = "/")
    public ResponseEntity<List<Project>> getProjects() {
        List<Project> list = projectRepository.findAll();
        return ResponseEntity.ok(list);
    }
}
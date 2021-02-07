package com.silviu.activiti2.controllers;

import com.silviu.activiti2.pojo.SparePart;
import com.silviu.activiti2.services.ActivitiService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MaintenanceAgentController {

    @Autowired
    ActivitiService activitiService;

    @GetMapping("/maintenance")
    public ModelAndView maintenanceGetTasks() {
        List<Task> taskList = activitiService.getTasks("MaintenanceAgent");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("maintenacePage");
        modelAndView.addObject("taskList", taskList);

        return modelAndView;
    }

    @PostMapping(value="/maintenance/requestPart")
    public ModelAndView requestPart(@ModelAttribute SparePart sparePart) {
        activitiService.startProcess(sparePart.getPartName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("requestPart");
        modelAndView.addObject("sparePart", sparePart);
        return modelAndView;
    }

    @GetMapping("/maintenance/completeTask/{taskId}")
    public String completeTask(@PathVariable("taskId") String taskId) {
        activitiService.completeTask(taskId);
        return "maintenacePage";
    }
}

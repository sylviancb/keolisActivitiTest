package com.silviu.activiti2.controllers;

import com.silviu.activiti2.services.ActivitiService;
import com.silviu.activiti2.services.PartsStoreService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SparePartsBuyerController {

    @Autowired
    ActivitiService activitiService;

    @Autowired
    PartsStoreService partsStoreService;

    private String taskId;

    @GetMapping("/buyer/taskList")
    public ModelAndView buyPartsTasks() {
        List<Task> taskList = activitiService.getTasks("PartsBuyer");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("taskList");
        modelAndView.addObject("taskList", taskList);

        Integer[] array = {1, 3, 4, 2};

        Arrays.sort(array);

        return modelAndView;
    }

    @GetMapping("/buyer/partStore/{taskId}")
    public ModelAndView buyNewPart(@PathVariable("taskId") String taskId) {
        this.taskId = taskId;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("partStore");
        modelAndView.addObject("availableParts", partsStoreService.getAvailablePartsInStore());

        return modelAndView;
    }

    @GetMapping("/buyer/completeBuy/{partName}")
    public ModelAndView completePartBuy(@PathVariable("partName") String partName) {

        if(taskId != null && !taskId.isEmpty()) {
            Map<String, Object> varibles = new HashMap<>();
            varibles.put("boughtPart", partName);
            activitiService.completeTaskWithVariables(taskId, varibles);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("completeBuy");
        modelAndView.addObject("partName", partName);

        return modelAndView;
    }
}

package com.silviu.activiti2.services;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivitiService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    public void startProcess(String requestedPart){
        Map<String, Object> requestPartVars = new HashMap<>();
        requestPartVars.put("requestedPart", requestedPart);

        runtimeService.startProcessInstanceByKey("spareParts", requestPartVars);

        System.out.println("process started: " + runtimeService.createProcessInstanceQuery().count());
    }

    public List<Task> getTasks(String username) {
        return taskService.createTaskQuery().taskCandidateGroup(username).list();
    }

    public void completeTaskWithVariables(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
    }

    public void completeTask(String taskId) {
        taskService.complete(taskId);
    }
}

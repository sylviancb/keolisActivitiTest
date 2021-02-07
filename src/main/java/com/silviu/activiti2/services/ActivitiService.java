package com.silviu.activiti2.services;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivitiService {

    private static Logger log = LoggerFactory.getLogger(ActivitiService.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    public void startProcess(String requestedPart){
        Map<String, Object> requestPartVars = new HashMap<>();
        requestPartVars.put("requestedPart", requestedPart);

        runtimeService.startProcessInstanceByKey("spareParts", requestPartVars);

        log.info("process started: " + runtimeService.createProcessInstanceQuery().count());
    }

    public List<Task> getTasks(String username) {
        List<Task> taskList = taskService.createTaskQuery().taskCandidateGroup(username).list();
        log.info("found " + taskList.size() + " tasks for user: " + username);
        return taskList;
    }

    public void completeTaskWithVariables(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
        log.info("task id: " + taskId + " completed");
    }

    public void completeTask(String taskId) {
        taskService.complete(taskId);
        log.info("task id: " + taskId + " completed");
    }
}

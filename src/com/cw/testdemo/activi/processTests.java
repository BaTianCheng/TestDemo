package com.cw.testdemo.activi;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;

public class processTests {

	public static void main(String[] args) {
		// 加载配置文件
	    ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("com/cw/testdemo/activi/activiti.cfg.xml").buildProcessEngine();
	    RepositoryService repositoryService = processEngine.getRepositoryService();
	    RuntimeService runtimeService = processEngine.getRuntimeService();
	    repositoryService.createDeployment().addClasspathResource("com/cw/testdemo/activi/test.bpmn20.xml").deploy();
	    String processId = runtimeService.startProcessInstanceByKey("financialReport").getId();
	    
	    TaskService taskService = processEngine.getTaskService();
	    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("sales").list();
	    
	    for (Task task : tasks) {
            System.out.println("Following task is available for sales group: " + task.getName());
            // 认领任务这里由foozie认领，因为fozzie是sales组的成员
            taskService.claim(task.getId(), "fozzie");
        }
	    
	 // 查看fozzie现在是否能够获取到该任务
        tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
        for (Task task : tasks) {
            System.out.println("Task for fozzie: " + task.getName());
            // 执行(完成)任务
            taskService.complete(task.getId());
        }
        // 现在fozzie的可执行任务数就为0了
        System.out.println("Number of tasks for fozzie: "
                           + taskService.createTaskQuery().taskAssignee("fozzie").count());
        // 获得第二个任务
        tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            System.out.println("Following task is available for accountancy group:" + task.getName());
            // 认领任务这里由kermit认领，因为kermit是management组的成员
            taskService.claim(task.getId(), "kermit");
        }
        // 完成第二个任务结束流程
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }
        // 核实流程是否结束,输出流程结束时间
	    HistoryService historyService = processEngine.getHistoryService();
	    HistoricProcessInstance historicProcessInstance = historyService
	            .createHistoricProcessInstanceQuery()
	            .processInstanceId(processId).singleResult();
	    System.out.println("\nendTime："+historicProcessInstance.getEndTime());
	    
//	    //得到笔试的流程
//	    System.out.println("\n***************笔试流程开始***************");
//
//	    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("人力资源部").list();
//	    for (Task task : tasks) {
//	        System.out.println("人力资源部的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "张三");
//	    }
//
//	    System.out.println("张三的任务数量："+taskService.createTaskQuery().taskAssignee("张三").count());
//	    tasks = taskService.createTaskQuery().taskAssignee("张三").list();
//	    for (Task task : tasks) {
//	        System.out.println("张三的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//
//	    System.out.println("张三的任务数量："+taskService.createTaskQuery().taskAssignee("张三").count());
//	    System.out.println("***************笔试流程结束***************");
//
//	    System.out.println("\n***************一面流程开始***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("技术部").list();
//	    for (Task task : tasks) {
//	        System.out.println("技术部的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "李四");
//	    }
//
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    for (Task task : tasks) {
//	        System.out.println("李四的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    System.out.println("***************一面流程结束***************");
//
//	    System.out.println("\n***************二面流程开始***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("技术部").list();
//	    for (Task task : tasks) {
//	        System.out.println("技术部的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "李四");
//	    }
//
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    for (Task task : tasks) {
//	        System.out.println("李四的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    System.out.println("***************二面流程结束***************");
//
//	    System.out.println("***************HR面流程开始***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("人力资源部").list();
//	    for (Task task : tasks) {
//	        System.out.println("技术部的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "李四");
//	    }
//
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    for (Task task : tasks) {
//	        System.out.println("李四的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    System.out.println("***************HR面流程结束***************");
//
//	    System.out.println("\n***************录用流程开始***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("人力资源部").list();
//	    for (Task task : tasks) {
//	        System.out.println("技术部的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "李四");
//	    }
//
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    for (Task task : tasks) {
//	        System.out.println("李四的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    System.out.println("***************录用流程结束***************");
//
//	    HistoryService historyService = processEngine.getHistoryService();
//	    HistoricProcessInstance historicProcessInstance = historyService
//	            .createHistoricProcessInstanceQuery()
//	            .processInstanceId(processId).singleResult();
//	    System.out.println("\n流程结束时间："+historicProcessInstance.getEndTime());
	}

}

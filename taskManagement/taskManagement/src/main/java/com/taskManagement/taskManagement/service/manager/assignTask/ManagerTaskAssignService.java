package com.taskManagement.taskManagement.service.manager.assignTask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskManagement.taskManagement.dao.statusNameList.StatusNameListDAO;
import com.taskManagement.taskManagement.dao.task.TaskAlloteDAO;
import com.taskManagement.taskManagement.external.services.EmployeeDetailsExternalService;
import com.taskManagement.taskManagement.model.statusNameList.StatusNameListModel;
import com.taskManagement.taskManagement.model.task.TaskAlloteModel;
import com.taskManagement.taskManagement.model.task.TaskCommentModel;
import com.taskManagement.taskManagement.request.manager.assignTask.ManagerAssignTaskRequest;
import com.taskManagement.taskManagement.request.manager.assignTask.ManagerCommentRequest;
import com.taskManagement.taskManagement.request.microservice.EmployeeAllDetails;
import com.taskManagement.taskManagement.request.microservice.EmployeeDetails;
import com.taskManagement.taskManagement.request.microservice.EmployeeList;
import com.taskManagement.taskManagement.response.employeeTask.CommentResponse;
import com.taskManagement.taskManagement.response.manager.getAllEmp.ManagerGetAllEmployeeResponse;
import com.taskManagement.taskManagement.response.manager.getAllEmp.MgrCommentResponse;

@Service
public class ManagerTaskAssignService {

	@Autowired
	private EmployeeDetailsExternalService employeeDetailsExternalService;

	@Autowired
	private TaskAlloteDAO taskAlloteDAO;

	@Autowired
	private StatusNameListDAO statusNameListDAO;

	private TaskAlloteModel taskAlloteModel;
	private StatusNameListModel statusNameListModel;

	// get all employee assign task list and status
	public ResponseEntity<?> getAllEmployeeTaskList() throws Exception {
		try {
			EmployeeDetails loginDetail = employeeDetailsExternalService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			EmployeeList employeeList = employeeDetailsExternalService.getEmpListUnderManager();
			if (employeeList.getEmployeeDetailsList() != null || !employeeList.getEmployeeDetailsList().isEmpty()) {
				List<ManagerGetAllEmployeeResponse> response = new ArrayList<ManagerGetAllEmployeeResponse>();
				for (EmployeeAllDetails empDetails : employeeList.getEmployeeDetailsList()) {
					List<TaskAlloteModel> taskList = taskAlloteDAO
							.findByempEmail(empDetails.getEmpEmail().toLowerCase());
					// for(TaskAlloteModel task:taskList) {
					taskList.stream().map(task -> {
						// getting all comment this task and then sort it
						List<TaskCommentModel> taskCommentModelList = task.getTaskCommentList();
						taskCommentModelList.sort((t1, t2) -> t1.getId().compareTo(t2.getId()));
						List<MgrCommentResponse> commentList = taskCommentModelList.stream().map(comment -> {
							MgrCommentResponse commentName = new MgrCommentResponse();
							commentName.setComment(comment.getComment());
							return commentName;
						}).collect(Collectors.toList());
						ManagerGetAllEmployeeResponse empTask = new ManagerGetAllEmployeeResponse();
						empTask.setEmpEmail(task.getEmpEmail());
						empTask.setEmpName(empDetails.getEmpName());
						empTask.setEmpDesignation(empDetails.getEmpDesignation());
						empTask.setEmpRole(empDetails.getEmpRole());
						empTask.setTaskId(task.getTaskId());
						empTask.setTaskAssign(task.getTaskAssign());
						empTask.setDescription(task.getDescription());
						empTask.setPriority(task.getPriority());
						empTask.setCommentList(commentList);
						empTask.setAssignDate(task.getAssignDate());
						empTask.setUpdateDate(task.getUpdateDate());
						empTask.setStatus(task.getStatus());
						response.add(empTask);
						return null;
					}).collect(Collectors.toList());
				}
				response.sort((e1, e2) -> e1.getAssignDate().compareTo(e2.getAssignDate()));
				if (!response.isEmpty()) {
					return ResponseEntity.status(HttpStatus.OK).body(response);
				} else {
					return ResponseEntity.status(HttpStatus.OK)
							.body("you do not assign any task to any employee till now");
				}
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not have any employee assign to you");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// Assign task to employee
	public ResponseEntity<?> addEmployeeTask(ManagerAssignTaskRequest addRequest) throws Exception {
		try {
			EmployeeDetails loginDetail = employeeDetailsExternalService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			if (addRequest == null || addRequest.getEmpEmail() == null || addRequest.getEmpEmail().isEmpty()
					|| addRequest.getTaskName() == null || addRequest.getTaskName().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Employee Email and Task Name could not be null");
			}
			if (addRequest.getEmpEmail().toLowerCase().equals(loginDetail.getEmpEmail().toLowerCase())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can not assign task to itself");
			}
			if (employeeDetailsExternalService.getIsManager(addRequest.getEmpEmail().toLowerCase())) {
				LocalDate assignDate = LocalDate.now();
				statusNameListModel = statusNameListDAO.findBystatusName("ASSIGN");
				if (statusNameListModel == null) {
					StatusNameListModel addStatus = new StatusNameListModel();
					addStatus.setStatusName("ASSIGN");
					addStatus = statusNameListDAO.save(addStatus);
					if (addStatus == null) {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								.body("ASSIGN option not added into assign menu please add first");
					}
				}
				TaskAlloteModel taskAssign = new TaskAlloteModel();
				taskAssign.setEmpEmail(addRequest.getEmpEmail().toLowerCase());
				taskAssign.setTaskAssign(addRequest.getTaskName());
				taskAssign.setAssignDate(assignDate);
				taskAssign.setStatus("ASSIGN");
				if (addRequest.getTaskDescription() != null || !addRequest.getTaskDescription().isEmpty()) {
					taskAssign.setDescription(addRequest.getTaskDescription());
				}
				if (addRequest.getPriority() != null || !addRequest.getPriority().isEmpty()) {
					taskAssign.setPriority(addRequest.getPriority());
				}
				if (addRequest.getComment() != null || !addRequest.getComment().isEmpty()) {
					List<TaskCommentModel> commentList = new ArrayList<TaskCommentModel>();
					StringBuilder commentBuilder = new StringBuilder();
					commentBuilder.append("'Manager': ");
					commentBuilder.append(addRequest.getComment());
					String comment = commentBuilder.toString();
					TaskCommentModel commentModel = new TaskCommentModel();
					commentModel.setComment(comment);
					commentModel.setTaskAlloteModel(taskAssign);
					commentList.add(commentModel);
					taskAssign.setTaskCommentList(commentList);
				}
				taskAssign = taskAlloteDAO.save(taskAssign);
				if (taskAssign != null) {
					return ResponseEntity.status(HttpStatus.CREATED).body("Task Assign Successfully");
				} else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
				}
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not a Manager of this employee");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// add task comment or give reply of employee comment
	public ResponseEntity<?> addTaskComment(ManagerCommentRequest addComment) throws Exception {
		try {
			EmployeeDetails loginDetail = employeeDetailsExternalService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			if (addComment == null || addComment.getTaskId() == null || addComment.getEmpEmail() == null
					|| addComment.getEmpEmail().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All field are empty or TaskId or Employee Email is Empty");
			}
			taskAlloteModel = taskAlloteDAO.findBytaskId(addComment.getTaskId());
			if (taskAlloteModel != null) {
				if (taskAlloteModel.getEmpEmail().toLowerCase().equals(addComment.getEmpEmail().toLowerCase())) {

					if (employeeDetailsExternalService.getIsManager(addComment.getEmpEmail().toLowerCase())) {
						StringBuilder commentBuilder = new StringBuilder();
						commentBuilder.append("'Manager': ");
						commentBuilder.append(addComment.getComment());
						String comment = commentBuilder.toString();
						TaskCommentModel taskComment = new TaskCommentModel();
						taskComment.setComment(comment);
						taskComment.setTaskAlloteModel(taskAlloteModel);
						List<TaskCommentModel> addTaskList = taskAlloteModel.getTaskCommentList();
						addTaskList.add(taskComment);
						taskAlloteModel.setTaskCommentList(addTaskList);
						taskAlloteModel = taskAlloteDAO.save(taskAlloteModel);
						if (taskAlloteModel != null) {
							return ResponseEntity.status(HttpStatus.OK).body("Task comment added");
						} else {
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
									.body("Some error occure in adding task comment");
						}
					}else {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you are not the manager of this employee");
					}
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task Employee Email not match with provided email :"+addComment.getEmpEmail().toLowerCase());
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not have any task assign with this task id: "+addComment.getTaskId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}

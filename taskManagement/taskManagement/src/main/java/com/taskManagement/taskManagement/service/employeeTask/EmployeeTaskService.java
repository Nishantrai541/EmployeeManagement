package com.taskManagement.taskManagement.service.employeeTask;

import java.time.LocalDate;
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
import com.taskManagement.taskManagement.request.employee.updateTaskStatus.UpdateTaskStatusRequest;
import com.taskManagement.taskManagement.request.microservice.EmployeeDetails;
import com.taskManagement.taskManagement.response.employeeTask.CommentResponse;
import com.taskManagement.taskManagement.response.employeeTask.EmpolyeeTaskResponse;

@Service
public class EmployeeTaskService {

	@Autowired
	private EmployeeDetailsExternalService employeeDetailsExternalService;

	@Autowired
	private TaskAlloteDAO taskAlloteDAO;

	@Autowired
	private StatusNameListDAO statusNameListDAO;

	private TaskAlloteModel taskAlloteModel;
	private StatusNameListModel statusNameListModel;

	// EmpolyeeTaskResponse

	// get list of all task
	public ResponseEntity<?> getAssignTask() {
		try {
			EmployeeDetails loginDetail = employeeDetailsExternalService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			List<TaskAlloteModel> allTaskList = taskAlloteDAO.findByempEmail(loginDetail.getEmpEmail().toLowerCase());
			if (!allTaskList.isEmpty()) {
				List<EmpolyeeTaskResponse> response = allTaskList.stream().map(task -> {
					//getting comment for this particular task
					List<TaskCommentModel> taskCommentModelList = task.getTaskCommentList();
					taskCommentModelList.sort((t1, t2) -> t1.getId().compareTo(t2.getId()));
					List<CommentResponse> commentList = taskCommentModelList.stream().map(comment -> {
						CommentResponse commentName = new CommentResponse();
						commentName.setComment(comment.getComment());
						return commentName;
					}).collect(Collectors.toList());
					EmpolyeeTaskResponse employeeTaskResponse = new EmpolyeeTaskResponse();
					employeeTaskResponse.setTaskId(task.getTaskId());
					employeeTaskResponse.setTaskAssign(task.getTaskAssign());
					employeeTaskResponse.setDescription(task.getDescription());
					employeeTaskResponse.setPriority(task.getPriority());
					employeeTaskResponse.setCommentResponse(commentList);
					employeeTaskResponse.setAssignDate(task.getAssignDate());
					employeeTaskResponse.setUpdateDate(task.getUpdateDate());
					employeeTaskResponse.setStatus(task.getStatus());
					return employeeTaskResponse;
				}).collect(Collectors.toList());
				response.sort((e1, e2) -> e1.getAssignDate().compareTo(e2.getAssignDate()));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("you do not have any task assign till now");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// update task or add comment request of employee
	public ResponseEntity<?> updateAssignTaskStatus(UpdateTaskStatusRequest updateTask) throws Exception {
		try {
			EmployeeDetails loginDetail = employeeDetailsExternalService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			if (updateTask == null || updateTask.getTaskId() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Any field must not be empty, All field are required");
			}
			taskAlloteModel = taskAlloteDAO.findBytaskId(updateTask.getTaskId());
			if (taskAlloteModel != null) {
				if (loginDetail.getEmpEmail().toLowerCase().equals(taskAlloteModel.getEmpEmail().toLowerCase())) {
					if (updateTask.getStatus() == null || updateTask.getStatus().isEmpty()) {
						if (updateTask.getComment() != null && !(updateTask.getComment().isEmpty())) {
							StringBuilder commentBuilder = new StringBuilder();
							commentBuilder.append("'Employee': ");
							commentBuilder.append(updateTask.getComment());
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
						} else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST)
									.body("Task status and comment both could not be Empty");
						}
					}
					statusNameListModel = statusNameListDAO.findBystatusName(updateTask.getStatus().toUpperCase());
					if (statusNameListModel != null) {
						LocalDate updateDate = LocalDate.now();
						taskAlloteModel.setUpdateDate(updateDate);
						taskAlloteModel.setStatus(statusNameListModel.getStatusName().toUpperCase());
						if (updateTask.getComment() != null && !updateTask.getComment().isEmpty()) {
							StringBuilder commentBuilder = new StringBuilder();
							commentBuilder.append("'Employee': ");
							commentBuilder.append(updateTask.getComment());
							String comment = commentBuilder.toString();
							TaskCommentModel taskComment = new TaskCommentModel();
							taskComment.setComment(comment);
							taskComment.setTaskAlloteModel(taskAlloteModel);
							List<TaskCommentModel> addTaskList = taskAlloteModel.getTaskCommentList();
							addTaskList.add(taskComment);
							taskAlloteModel.setTaskCommentList(addTaskList);
						}
						taskAlloteModel = taskAlloteDAO.save(taskAlloteModel);
						if (taskAlloteModel != null) {
							return ResponseEntity.status(HttpStatus.OK).body("Task status update successfully");
						} else {
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
						}
					} else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body("Status name is not present in status option, Please choose the right option");
					}

				} else {
					return ResponseEntity.status(HttpStatus.FORBIDDEN)
							.body("This Task not belongs to you with this TaskID :" + updateTask.getTaskId());
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Not have any Task assign with this form id :" + updateTask.getTaskId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}

package io.anush.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splunk.Receiver;
import com.splunk.Service;

import io.anush.connection.SplunkConnection;
import io.anush.model.Department;
import io.anush.model.DepartmentList;
import io.anush.model.Employee;
import io.anush.model.EmployeeList;
import io.anush.service.DepartmentService;
import io.anush.service.EmployeeService;

@RestController
@RequestMapping("/Department")
public class DepartmentController {
	Service service;
	Receiver receive;
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/listDept")
	public DepartmentList getAllDepartments() {
		List<Department> list = departmentService.getAllDepartments();
		DepartmentList listOfDepartments = new DepartmentList();
		listOfDepartments.setDeptList(list);
		logger.info("Getting All department list");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In DepartmentInfoService controller getting the list of departments...");
		return listOfDepartments;
	}

	@RequestMapping("/{deptId}")
	public Optional<Department> getDepartment(@PathVariable("deptId") int deptId) {
		logger.info("Getting  Department details");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In DepartmentInfoService controller  getting the department details...");
		return departmentService.getDepartment(deptId);
	}

	@PostMapping("/addDepartment")
	public Department addDepartment(@RequestBody Department department) {
		logger.info("Adding department");

		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In DepartmentInfoService Controller  adding Department...");
		return departmentService.addDepartment(department);
	}

	@PutMapping("/updateDepartment/{deptId}")
	public void updateDepartment(@RequestBody Department department, @PathVariable int deptId) {
		logger.info("update the department");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In DepartmentInfoService Controller  updating the Department Name...");
		department.setDeptId(deptId);
		departmentService.updateDepartment(department);
	}

	@DeleteMapping("/deleteDepartment/{deptId}")
	public void deleteDepartment(@PathVariable int deptId) {
		logger.info("deleting the Department");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In DepartmentInfoService Controller  Deleting the department details...");
		departmentService.deleteDepartment(deptId);
		employeeService.deleteEmployee(deptId);
	}

	@GetMapping("{eDid}/employees")
	public EmployeeList getAllEmployees(@PathVariable int eDid) {
		logger.info("Getting All Employees by did");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In DepartmentInfoService Controller  getting the employess details of particular Did...");
		EmployeeList lst = employeeService.getAllEmployees(eDid);
		System.out.println(lst.getListOfEmployees().size());
		System.out.println(lst.getListOfEmployees().size());
		return lst;

	}

	@GetMapping("/employees/{empId}")
	public Employee getEmployee(@PathVariable int empId) {
		logger.info("details of Employee");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In DepartmentInfoService controller getting employess");
		return employeeService.getEmployee(empId);

	}

	@PostMapping("/employees/{eDid}/addEmployee")
	public void addEmployee(@RequestBody Employee employee, @PathVariable int eDid) {
		logger.info("Adding  Employee");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In DepartmentInfoController controller Adding the Employee details...");
		employee.seteDid(eDid);
		employeeService.addEmployee(employee, eDid);
	}

	@PutMapping("/employees/{eDid}/updateEmployee/{empId}")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable int empId, @PathVariable int eDid) {
		logger.info("updating the Employee");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In DepartmentInfoController controller updating the employee details...");
		employee.setEmpId(empId);
		employee.seteDid(eDid);
		employeeService.updateEmployee(employee, eDid, empId);
	}

	@DeleteMapping("/employees/deleteEmployee/{eDid}")
	public void deleteEmployee(@PathVariable int eDid) {
		logger.info("Deleting the Employees of did");

		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main",
				"In DepartmentInfoController controller Deleting the all employee details in particular Did...");
		employeeService.deleteEmployee(eDid);
	}

	@DeleteMapping("/employees/{eDid}/deleteEmployee/{empId}")
	public void deleteSingleEmployee(@PathVariable int eDid, @PathVariable int empId) {
		logger.info("Deleting single Employee");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In DepartmentInfoController controller Deleting the Employee details...");
		employeeService.deleteSingleEmployee(eDid, empId);
	}

}

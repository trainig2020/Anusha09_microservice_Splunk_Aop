package io.anush.controller;

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
import io.anush.model.Employee;
import io.anush.model.EmployeeList;
import io.anush.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	Service service;
	Receiver receive;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/listEmp/{eDid}")
	public EmployeeList getAllEmployees(@PathVariable int eDid) {
		logger.info("Getting All Employees");

		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In EmployeeInfoService controller getting the list of Employees...");
		return employeeService.getAllEmployess(eDid);
	}

	@GetMapping("/{empId}")
	public Optional<Employee> getEmployee(@PathVariable int empId) {
		logger.info("Getting All Employee details of the particular id");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In EmployeeInfoService controller getting the Employee details...");
		return employeeService.GetEmployee(empId);
	}

	@PostMapping("/{eDid}/addEmployee")
	public void addEmployee(@RequestBody Employee employee, @PathVariable int eDid) {
		logger.info("adding  Employees");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In EmployeeInfoService controller adding the Employee...");
		employee.seteDid(eDid);
		employeeService.addEmployee(employee);
	}

	@PutMapping("/{eDid}/updateEmployee/{empId}")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable int empId, @PathVariable int eDid) {
		logger.info("updating  Employees");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In EmployeeInfoService controller updating the Employee details...");
		employee.setEmpId(empId);
		employee.seteDid(eDid);
		employeeService.updateEmployee(employee);
	}

	@DeleteMapping("/deleteEmployee/{eDid}")
	public void deleteEmployee(@PathVariable int eDid) {
		logger.info("deleting all Employees");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In EmployeeInfoService controller deleting the Employee ...");
		employeeService.deleteEmployee(eDid);

	}

	@DeleteMapping("/deleteAll/{eDid}/{empId}")
	public void deleteEmployeeByEdidAndEmpid(@PathVariable int eDid, @PathVariable int empId) {
		logger.info("deleting particular Employee");
		service = SplunkConnection.getService();
		receive = service.getReceiver();
		receive.log("main", "In EmployeeInfoService controller deleting the Employees of Did...");
		employeeService.deleteEmployeeByEdid(eDid, empId);
	}

}

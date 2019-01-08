package com.appleyk.controller;

import com.appleyk.node.Employee;
import com.appleyk.repository.EmployeeRepository;
import com.appleyk.result.ResponseMessage;
import com.appleyk.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/v1.0.1/database/employee")
public class EmployeeController {

	@Autowired
    EmployeeRepository employeeRepository;

	@RequestMapping("/get")
	public ResponseResult GetEmployees(@RequestParam(value = "name") String name) {
		Employee employee = employeeRepository.findByName(name);

		if (employee != null) {
			// 打印下name的关系，以下关系！=null，就是一个set集合，集合未做处理！
			System.out.println(employee.getManages());
			System.out.println("========上管理【" + employee.getName() + "】下同事============");
			System.out.println(employee.getColleagues());

			return new ResponseResult(ResponseMessage.OK);
		}
		return new ResponseResult(ResponseMessage.INTERNAL_SERVER_ERROR);
	}
}

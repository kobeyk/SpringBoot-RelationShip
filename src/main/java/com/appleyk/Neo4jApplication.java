package com.appleyk;

import java.util.Arrays;
import java.util.List;

import com.appleyk.node.Employee;
import com.appleyk.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;



@SpringBootApplication// same as @Configuration @EnableAutoConfiguration  @ComponentScan
public class Neo4jApplication extends SpringBootServletInitializer{

	/**
	 * SpringApplication类提供了一种从main()方法启动Spring应用的便捷方式。 在很多情况下， 你只需委托给
	 * SpringApplication.run这个静态方法：
	 * 
	 * @param args
	 */
	public static void  main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Neo4jApplication.class);
	}
	
	
//	/**
//	 * Spring-Boot启动的时候，加载、创建、初始化数据
//	 * @param employeeRepository
//	 * @return
//	 */
//	@Bean
//	CommandLineRunner demo(EmployeeRepository employeeRepository) {
//		return args -> {
//
//			//先删除Employees节点，再创建
//			employeeRepository.deleteAll();
//
//			Employee boss = new Employee("总裁");
//			Employee manager= new Employee("项目经理");
//			Employee p1 = new Employee("员工1");
//			Employee p2 = new Employee("员工2");
//
//			List<Employee> team = Arrays.asList(boss,manager,p1,p2);
//
//			System.err.println("连接neo4j图库没创建关系节点之前...");
//
//			team.stream().forEach(person -> System.err.println("\t" + person.toString()));
//
//			employeeRepository.save(boss);
//			employeeRepository.save(manager);
//			employeeRepository.save(p1);
//			employeeRepository.save(p2);
//
//
//			boss = employeeRepository.findByName(boss.getName());
//
//			/*
//			 * Boss管理经理(一级直属关系)
//			 */
//			boss.management(manager);
//
//			/*
//			 * 同时 Boss和其他人是同事关系，反之亦成立
//			 */
//			boss.worksWith(manager);
//			boss.worksWith(p1);
//			boss.worksWith(p2);
//			//建立关系
//			employeeRepository.save(boss);
//
//
//			/*
//			 * 经理管理员工1和2(直接关系)
//			 * 先查节点，再添加关系（边）
//			 */
//			manager = employeeRepository.findByName(manager.getName());
//			manager.management(p1);
//			manager.management(p2);
//
//			/*
//			 * 由于上面我们已经知道了Boss和经理是同事，反之亦然，下面只添加经理和其他人的同事关系
//			 */
//			manager.worksWith(p1);
//			manager.worksWith(p2);
//
//			employeeRepository.save(manager);
//
//			/*
//			 * 员工1和员工2是同事关系，此层关系不考虑方向，因此创建一个就Ok
//			 */
//			p1 = employeeRepository.findByName(p1.getName());
//			p1.worksWith(p2);
//			employeeRepository.save(p1);
//
//			System.out.println("创建关系节点之后，通过人名查找节点查询节点信息...");
//			team.stream().forEach(person -> System.out.println(
//					"\t" + employeeRepository.findByName(person.getName()).toString()));
//		};
//	}

}

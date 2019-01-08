package com.appleyk.node;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NodeEntity
public class Employee {

	@GraphId
	private Long id;

	private String name;

	public Employee(String name) {
		this.name = name;
	}

	public Employee() {
	}

	/**
	 * Neo4j 并没有真正的双向关系，我们只有在查询的时候忽略关系的方向 可以参考下面这个链接对neo4j的关系作出正确的理解：
	 * https://dzone.com/articles/modelling-data-neo4j
	 */
	@Relationship(type = "同事", direction = Relationship.UNDIRECTED)
	// @Relationship(type = "同事")
	public Set<Employee> colleagues;

	@Relationship(type = "管理")
	public Set<Employee> manages;

	/*
	 * 指定同事关系 --->
	 */
	public void worksWith(Employee employee) {
		if (colleagues == null) {
			colleagues = new HashSet<>();
		}
		colleagues.add(employee);
	}

	/*
	 * 指定管理关系 --->
	 */
	public void management(Employee employee) {
		if (manages == null) {
			manages = new HashSet<>();
		}
		manages.add(employee);
	}

	/**
	 * 列出该节点（Employee）的关系网
	 */
	public String toString() {

		/*
		 * java8新特新 Optional.ofNullable(arg) 参数可以是null
		 * 如果值不为null，orElse方法返回Optional实例（关系）的值 Collections.emptySet():防止空指针出现 |
		 * | V 当代码需要一个集合而这个集合可能不存在，此时尽量使用空集合而不是null
		 */
		return this.name + "  同事 => "
				+ Optional.ofNullable(this.colleagues).orElse(Collections.emptySet()).stream()
						.map(employee -> employee.getName()).collect(Collectors.toList())
				+ " 管理 => " + Optional.ofNullable(this.manages).orElse(Collections.emptySet()).stream()
						.map(employee -> employee.getName()).collect(Collectors.toList());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getColleagues() {
		return colleagues;
	}

	public void setColleagues(Set<Employee> colleagues) {
		this.colleagues = colleagues;
	}

	public Set<Employee> getManages() {
		return manages;
	}

	public void setManages(Set<Employee> manages) {
		this.manages = manages;
	}
}
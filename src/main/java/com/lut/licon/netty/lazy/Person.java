package com.lut.licon.netty.lazy;

import java.util.Set;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/11 13:30
 */
public class Person {
	private Long id;

	private Lazy<String> department;

	private Lazy<Long> supervisor;

	private Lazy<Set<String>> permission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartment() {
		return department.get();
	}

	public void setDepartment(Lazy<String> department) {
		this.department = department;
	}

	public Long getSupervisor() {
		return supervisor.get();
	}

	public void setSupervisor(Lazy<Long> supervisor) {
		this.supervisor = supervisor;
	}

	public Set<String> getPermission() {
		return permission.get();
	}

	public void setPermission(Lazy<Set<String>> permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", department=" + department.get() +
				", supervisor=" + supervisor.get() +
				", permission=" + permission.get() +
				'}';
	}
}

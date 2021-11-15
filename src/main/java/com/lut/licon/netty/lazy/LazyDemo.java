package com.lut.licon.netty.lazy;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/11 13:17
 */
public class LazyDemo {
	public static void main(String[] args) {
		/*Supplier<Integer> a = ()->10+1;
		int b = a.get() + 1;
		System.out.println(b);*/

		Person person = new Person();
		person.setId(1L);
		person.setDepartment(Lazy.of(()->"应用开发事业群"));
		Set<String> permission = new HashSet<>();
		permission.add("select");
		permission.add("update");
		permission.add("delete");
		permission.add("add");
		person.setSupervisor(Lazy.of(()->13245678L));
		person.setPermission(Lazy.of(()->permission));

		Lazy<String> departmentLazy = Lazy.of(() -> "123456");
		Lazy<Long> supervisorLazy = departmentLazy.map(department -> Long.valueOf(department + "7"));
		System.out.println(supervisorLazy.get());

		Lazy<Lazy<Set<String>>> map = departmentLazy
				.map(department -> supervisorLazy.map(supervisor -> getPermission(department, supervisor)));
		System.out.println(map.get());

		Lazy<Set<String>> flatmap = departmentLazy
				.flatmap(department -> supervisorLazy.map(supervisor ->getPermission(department, supervisor)));
		System.out.println(flatmap.get());

	}

	public static Set<String> getPermission(String department,Long supervisor){
		return new HashSet<>();
	}
}

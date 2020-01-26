/*
 * This is a Jin-alpha Project
 * File name : Mapper.java
 * Created by : Jinhyun
 * Created on : Jan 2020 
 * Contents : Mapper
 */
package net.jin.dao;

import java.util.List;

import org.aspectj.weaver.ast.Test;
import org.springframework.data.repository.query.Param;

public interface Mapper {
	public List<Test> testlist(@Param("pagenum") int pagenum, @Param("contentnum") int contentnum);
	public int testcount();
}

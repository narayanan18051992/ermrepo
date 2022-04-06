package com.erms.batchprocessing;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.erms.model.Employee;
import com.erms.service.EmployeeService;

public class EmployeeItemWriter<T> implements ItemWriter<T> {

	@Autowired
	EmployeeService empService;

	@Override
	public void write(List<? extends T> items) throws Exception {  // writer remove duplicate entries and save in db.
		List<T> empDataWithoutDuplicates = items.stream().distinct().collect(Collectors.toList());
		for (T item : empDataWithoutDuplicates) {
			Employee emp = (Employee) item;
			empService.save(emp);
		}
	}
}

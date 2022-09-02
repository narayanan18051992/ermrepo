package com.erms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erms.batchprocessing.EmployeeItemProcessor;
import com.erms.model.Employee;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

@Component
public class CSVFileProcessor {

	private static final Logger logger = LoggerFactory.getLogger(CSVFileProcessor.class);


	public void processFile(String filePath) {
		
	
		 Observable<GroupedObservable<Integer, Employee>> groupObservable = Observable
				.defer(() -> new CsvFileObservableSource(filePath))
				.filter(ValidationProcessor::process)
				.groupBy(Employee::getId);
				

		groupObservable.subscribe(s -> {
		
		}, Throwable::printStackTrace, () -> {
			System.out.println("File Processing Complited");
		});
		

	}
}
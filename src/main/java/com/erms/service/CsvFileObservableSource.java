package com.erms.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.erms.model.Employee;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;

/**
 * 
 * @author Niraj Sonawane
 *
 */
public class CsvFileObservableSource implements ObservableSource<Employee> {

	private static final Logger log = LoggerFactory.getLogger(CsvFileObservableSource.class);
	private final String filename;

	CsvFileObservableSource(String filename) {
		this.filename = filename; 
	}

	@Override
	public void subscribe(Observer<? super Employee> observer) {
		try {
			Files.lines(Paths.get("input/employee.csv")).forEach(inputLine -> {
				
				String[] split = inputLine.split(",");
				Employee emp=new Employee(Integer.parseInt(split[0]),split[1],split[2],Integer.parseInt(split[3]));
				observer.onNext(emp);
			});
			observer.onComplete();
		} catch (IOException e) {
			observer.onError(e);
		}
	}

}

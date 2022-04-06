# ermrepo
Employee Record Management System(ERMS)

Note:

1.Use properties.file.path key in system environment properties or application.properties file to define ermconfig.properties file path. If the properties.file.path is not set then the default ermconfig.properties file path is /.
  (e.g. -Dproperties.file.path=<location for ermconfig.properties>
  
2.Use input.file.path key in ermconfig.properties file to define input csv file path. 
  
3.Below are the default properties defined in ermconfig.properties file.
    
    emp.dob=01-01-1995
    emp.age.category=Senior Employee
    emp.performance.level=High Performer
    input.file.path=input/employee.csv  //input csv file path
    line.fields=Id,name,dob,rating // can increase the field length as per number of columns in input csv file.
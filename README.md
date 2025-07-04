Requirements:
> Tomcat 9.0 (https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.106/bin/apache-tomcat-9.0.106-windows-x64.zip)
        Extract it and properly install in system(visit https://tomcat.apache.org for more info) 

How to run the provided appliication:
> clone the provided app in Tomcat 9.0/webapps
> create classes folder inside WEB-INF(Tomcat 9.0/webapps/WEB-INF) and compile all java files present inside src(Make same structure as src)
> simply run Tomcat server(Tomcat 9.0/bin/Tomcat9.exe) 
> by Default, server will run on port 8080 if not configured
> Application is ready and simply runs on http://localhost:8080/hms 


Note: If still application won't run(ofc wont run), it will required to add some jar files and properly configured in Envirnment Variables of system(windows)
It requires jar files like for jdbc, jstl, etc

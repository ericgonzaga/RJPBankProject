# RJPBankProject

RJP project for Equadis

# DATABASE

-   To run this project, is needed to start mysql database in docker using the following command:

   docker compose up

Then the database will be auto created, because of the configuration auto=create:

   File: resources/application.properties
   spring.jpa.hibernate.ddl-auto=create

For the second time ahead, it should good to comment this line there.

-   Other useful docker commands:

   docker exec -it rjpbankproject-mysql-1 bash
   mysql --password #verysecrect

# TESTS

Tests were created just to serve as example of how I develop my Unit and Integration tests in real project, but they are not working properly, since I had no time to finish all configurations needed.
There is also examples of how to call all endpoints created at resources/requests folder

# RUN

The easiest way to run this project is to use any IDE with Maven configured.
I had many problems to package the project as jar to execute manually, because of all maven and java configurations I have on my machine.
But the it would be using the following commands:

   mvn clean install

to package as jar and then execute on the same dir of jar, where java version must be 17:

   java com.equadis.bank.RjpBankProjectApplication

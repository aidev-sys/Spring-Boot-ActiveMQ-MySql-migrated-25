# Spring Boot ActiveMQ MySql

To create this example I used several examples that I found on the internet:

https://memorynotfound.com/spring-boot-embedded-activemq-configuration-example/

https://www.devglan.com/spring-boot/spring-boot-jms-activemq-example

https://www.onlinetutorialspoint.com/spring-boot/external-apache-activemq-spring-boot-example.html

https://grokonez.com/java-integration/distributed-system/activemq-producer-consumer-springboot-restapis-example

https://receitasdecodigo.com.br/spring-boot/exemplo-de-projeto-com-spring-boot-jms-activemq-usando-filas-e-topicos

https://github.com/mustafamym/jms-activemq-message-consumer-example

https://github.com/TechPrimers/standalone-spring-boot-activemq-example

To install MySql on Ubuntu:

https://www.digitalocean.com/community/tutorials/como-instalar-o-mysql-no-ubuntu-18-04-pt

https://www.linode.com/docs/databases/mysql/install-and-configure-mysql-workbench-on-ubuntu/


I had some problems with connection, and I run this command below, the password you have to change for your password:

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'admin123';


To run this example, you will need to do the following:

* Update the application.properties file with your MySQL username and password
* Create a new database schema with name springboot_activemq_example
* If you wish to used a different database / schema, you will need to override values in application.properties
* Example uses an embedded ActiveMQ Broker. If you wish to use an external broker, you will need to override the appropriate Spring Boot Properties.

Application flow:

* Once the application is started, open the same in a browser http://localhost:8080
* Create a new Product record
* Create a new Client record
* Notice that the value for "Are Messages Sent" in the http://localhost:8080/product/show/1 is set to false.
* Notice that the value for "Are Messages Sent" in the http://localhost:8080/client/show/1 is set to false.
* There is a link at the bottom of this record "Send message for product through queue listener"
* When this link is clicked, a message is put in an activemq queue, the browser is redirected back to the product show page without getting blocked.
* This message is read and processed by MessageListener class.
* On the browser, refreshing the page: http://localhost:8080/product/show/1
* On the browser, refreshing the page: http://localhost:8080/client/show/1
* You will see the message sent and message count increase (if you click the send link more than once)
* Expect a short delay in processing the messages.

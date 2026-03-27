package edson.springframework.controllers;

import jakarta.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edson.springframework.commands.ClientForm;
import edson.springframework.converters.ClientToClientForm;
import edson.springframework.domain.Client;
import edson.springframework.services.ClientService;

/**
 * @author edson 16/01/2019
 */
@Controller
public class ClientController {

    private static final Logger log = LogManager.getLogger();

    private ClientService clientService;

    private ClientToClientForm clientToClientForm;

    @Autowired
    public void setClientToClientForm(ClientToClientForm clientToClientForm) {
        this.clientToClientForm = clientToClientForm;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/c")
    public String redirToList(){
        return "redirect:/client/list";
    }

    @RequestMapping({"/client/list", "/client"})
    public String listClients(Model model){
        model.addAttribute("clients", clientService.listAll());
        return "client/list";
    }

    @RequestMapping("/client/show/{id}")
    public String getClient(@PathVariable String id, Model model){
        model.addAttribute("client", clientService.getById(Long.valueOf(id)));
        return "client/show";
    }

    @RequestMapping("client/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Client client = clientService.getById(Long.valueOf(id));
        ClientForm clientForm = clientToClientForm.convert(client);

        model.addAttribute("clientForm", clientForm);
        return "client/clientform";
    }

    @RequestMapping("/client/new")
    public String newClient(Model model){
        model.addAttribute("clientForm", new ClientForm());
        return "client/clientform";
    }

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public String saveOrUpdateClient(@Valid ClientForm clientForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "client/clientform";
        }

        Client savedClient = clientService.saveOrUpdateClientForm(clientForm);

        return "redirect:/client/show/" + savedClient.getId();
    }

    @RequestMapping("/client/delete/{id}")
    public String delete(@PathVariable String id){
        clientService.delete(Long.valueOf(id));
        return "redirect:/client/list";
    }

    @RequestMapping("/client/sendMessage/{id}")
    public String indexClient(@PathVariable String id){
        clientService.sendMessage(id);
        return "redirect:/client/show/"+id;
    }
}
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>edson.springframework</groupId>
    <artifactId>legacy-migration</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>legacy-migration</name>
    <description>Modernized Spring Boot application</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <properties>
        <java.version>21</java.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <!-- JPA / PostgreSQL -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Validation (Jakarta) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- RabbitMQ -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>

        <!-- Valkey / Redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <!-- Logging -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j-impl</artifactId>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-starter-data-mongodb</artifactId>
                        </exclude>
                        <exclude>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-starter-data-cassandra</artifactId>
                        </exclude>
                        <exclude>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
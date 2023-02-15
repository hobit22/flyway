package com.test.flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootApplication
public class FlywayApplication {

    private final EntityManager em;

    public FlywayApplication(EntityManager em) {
        this.em = em;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FlywayApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext run = app.run(args);

        FlywayApplication flywayApplication = (FlywayApplication) run.getBean("flywayApplication");

        Member member = new Member();
        member.setName("sample");

        flywayApplication.save(member);
    }

    @Transactional
    public void save(Member member) {
        em.persist(member);
    }
}

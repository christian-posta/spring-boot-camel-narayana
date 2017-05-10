package com.example;

import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

@SpringBootApplication
public class SpringBootNarayanaApplication {



	public static void main(String[] args) {
		SpringApplication.run(SpringBootNarayanaApplication.class, args);
		System.out.println("Running....");
	}

	@Bean
	public JmsComponent jms(ConnectionFactory xaJmsConnectionFactory, PlatformTransactionManager jtaTansactionManager){
		return  JmsComponent.jmsComponentTransacted(xaJmsConnectionFactory, jtaTansactionManager);
	}

	@Bean
	public SqlComponent sql(DataSource dataSource) {
		SqlComponent rc = new SqlComponent();
		rc.setDataSource(dataSource);
		return rc;
	}

	@Bean
	public JmsComponent nonTxJms(ConnectionFactory nonXaJmsConnectionFactory){
		return  JmsComponent.jmsComponentTransacted(nonXaJmsConnectionFactory);
	}

	@Bean(name = "PROPAGATION_REQUIRED")
	public SpringTransactionPolicy propogationRequired(PlatformTransactionManager jtaTransactionManager){
		SpringTransactionPolicy propagationRequired = new SpringTransactionPolicy();
		propagationRequired.setTransactionManager(jtaTransactionManager);
		propagationRequired.setPropagationBehaviorName("PROPAGATION_REQUIRED");
		return propagationRequired;

	}

}

package com.bridgelabz.usermanagement.config;

import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class UserManagementConfig {

	/**
	 * Purpose to return ObjectMapper Bean
	 * 
	 * @return ObjectMapper Bean
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * Purpose to return BCryptPasswordEncoder Bean
	 * 
	 * @return BCryptPasswordEncoder Bean
	 */
	@Bean
	public BCryptPasswordEncoder bcyBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Purpose to return Docket Bean to configure swagger
	 * 
	 * @return Docket Bean
	 */

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.bridgelabz.fundoo")).build();
	}
	

//    @Bean
//    public JavaMailSender javaMailService() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//
//        javaMailSender.setHost("myHost");
//        javaMailSender.setPort(25);
//
//        public
//        javaMailSender.setJavaMailProperties(getMailProperties());
//
//        return javaMailSender;
//    }
//
//    private Properties getMailProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("mail.transport.protocol", "smtp");
//        properties.setProperty("mail.smtp.auth", "false");
//        properties.setProperty("mail.smtp.starttls.enable", "false");
//        properties.setProperty("mail.debug", "false");
//        return properties;
//    }
    @Bean
    public JavaMailSender javamailsender()
    {
    	return new JavaMailSender() {
			
			@Override
			public void send(SimpleMailMessage... simpleMessages) throws MailException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void send(SimpleMailMessage simpleMessage) throws MailException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void send(MimeMessage... mimeMessages) throws MailException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void send(MimeMessage mimeMessage) throws MailException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public MimeMessage createMimeMessage() {
				// TODO Auto-generated method stub
				return null;
			}
		};
    }
}



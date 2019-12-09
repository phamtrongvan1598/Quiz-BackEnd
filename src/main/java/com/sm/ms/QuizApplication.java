package com.sm.ms;

import com.sm.ms.service.AnswerService;
import com.sm.ms.service.KindOfQuestionService;
import com.sm.ms.service.QuestionService;
import com.sm.ms.service.impl.AnswerServiceImpl;
import com.sm.ms.service.impl.KindOfQuestionServiceImpl;
import com.sm.ms.service.impl.QuestionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuizApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(QuizApplication.class, args);
    }
    @Bean
    public KindOfQuestionService kindOfQuestionService() {
        return new KindOfQuestionServiceImpl();
    }

    @Bean
    public AnswerService answerService() {
        return new AnswerServiceImpl();
    }

    @Bean
    public QuestionService questionService() {
        return new QuestionServiceImpl();
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(QuizApplication.class);
    }

}

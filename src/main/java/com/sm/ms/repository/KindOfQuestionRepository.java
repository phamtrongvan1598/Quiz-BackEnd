package com.sm.ms.repository;


import com.sm.ms.model.KindOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KindOfQuestionRepository extends JpaRepository<KindOfQuestion, Long> {
//    KindOfQuestion findByTitle(String kindOfQuestion);
//
//    List<KindOfQuestion> findAllByWriterUsername(String username);

}

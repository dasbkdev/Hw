package homework.jobsearch.repository;

import homework.jobsearch.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByRespondedApplicantsOrderByTimestamp(Long respondedApplicants);
}
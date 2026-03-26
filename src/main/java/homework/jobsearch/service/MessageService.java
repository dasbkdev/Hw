package homework.jobsearch.service;

import homework.jobsearch.dao.MessageDao;
import homework.jobsearch.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageDao messageDao;

    public List<Message> getMessagesByResponseId(Long responseId) {
        return messageDao.getMessagesByResponseId(responseId);
    }
}
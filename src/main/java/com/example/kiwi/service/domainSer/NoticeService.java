package com.example.kiwi.service.domainSer;
import com.example.kiwi.domain.notice.Notice;
import com.example.kiwi.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    public Optional<Notice> getNoticeById(Long id) {
        return noticeRepository.findById(id);
    }


    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Notice updateNotice(Long id, Notice noticeDetails) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));
        notice.setTitle(noticeDetails.getTitle());
        notice.setContent(noticeDetails.getContent());
        return noticeRepository.save(notice);
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

}
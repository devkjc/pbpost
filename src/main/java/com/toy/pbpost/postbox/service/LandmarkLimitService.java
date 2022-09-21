package com.toy.pbpost.postbox.service;

import com.toy.pbpost.common.util.DateTimeUtil;
import com.toy.pbpost.letter.dto.LandmarkLimitDto;
import com.toy.pbpost.letter.repository.LandmarkLimitRepository;
import com.toy.pbpost.postbox.domain.LandmarkLimit;
import com.toy.pbpost.user.domain.User;
import com.toy.pbpost.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LandmarkLimitService {

    private final LandmarkLimitRepository landmarkLimitRepository;
    private final UserService userService;

    public LandmarkLimitDto.Res getLandmarkLimit(String uid) {
        User user = userService.getUser(uid);
        String timezone = user.getTimezone();
        LocalDate today = DateTimeUtil.saveZoneDateTime(LocalDateTime.now(), timezone).toLocalDate();
        return LandmarkLimitDto.Res.of(getLandmarkLimit(uid, timezone, today));
    }

    @Transactional
    public void addCount(String uid, String timezone) {
        LocalDate today = DateTimeUtil.saveZoneDateTime(LocalDateTime.now(), timezone).toLocalDate();
        addCount(uid, timezone, today);
    }

    private void addCount(String uid, String timezone, LocalDate today) {
        LandmarkLimit landmarkLimit = getLandmarkLimit(uid, timezone, today);
        String beforeTimezone = landmarkLimit.getTimezone();
        if (beforeTimezone.equals(timezone)) {
            landmarkLimit.addCount();
        } else {
            // timezone 을 오늘 바꾼 사람이라면
            LocalDate beforeDate = DateTimeUtil.saveZoneDateTime(LocalDateTime.now(), beforeTimezone).toLocalDate();
            addCount(uid, beforeTimezone, beforeDate);
        }
    }

    private LandmarkLimit getLandmarkLimit(String uid, String timezone, LocalDate today) {
        return landmarkLimitRepository.findByUidAndDate(uid, today).orElseGet(() -> landmarkLimitRepository.save(LandmarkLimit.builder()
                .uid(uid)
                .date(today)
                .count(0L)
                .timezone(timezone)
                .build()));
    }
}

package com.toy.pbpost.letter.service;

import com.toy.pbpost.bird.domain.Bird;
import com.toy.pbpost.bird.service.BirdService;
import com.toy.pbpost.common.domain.Address;
import com.toy.pbpost.common.util.LocationDistanceService;
import com.toy.pbpost.letter.domain.Letter;
import com.toy.pbpost.letter.domain.LetterBackground;
import com.toy.pbpost.letter.domain.LetterFont;
import com.toy.pbpost.letter.dto.LetterDto;
import com.toy.pbpost.letter.repository.LetterBackgroundRepository;
import com.toy.pbpost.letter.repository.LetterFontRepository;
import com.toy.pbpost.letter.repository.LetterRepository;
import com.toy.pbpost.postbox.domain.Landmark;
import com.toy.pbpost.postbox.domain.PostBox;
import com.toy.pbpost.postbox.service.LandmarkService;
import com.toy.pbpost.postbox.service.PostBoxService;
import com.toy.pbpost.user.domain.User;
import com.toy.pbpost.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;
    private final LetterFontRepository letterFontRepository;
    private final LetterBackgroundRepository letterBackgroundRepository;
    private final LandmarkService landmarkService;
    private final PostBoxService postBoxService;
    private final LocationDistanceService locationDistanceService;
    private final UserService userService;
    private final BirdService birdService;

    @Transactional
    public LetterDto.Res saveLetter(String fromUid, LetterDto.Req req) {
        User fromUser = userService.getUser(fromUid);
        return LetterDto.Res.of(letterRepository.save(toEntity(fromUser, req)), fromUser.getTimezone());
    }

    private Letter toEntity(User fromUser, LetterDto.Req req) {

        User toUser = null;
        Landmark landmark = null;
        PostBox postBox = null;
        LocalDateTime departureTime = LocalDateTime.now(Clock.systemUTC());
        LocalDateTime arrivalTime;

        Bird bird = birdService.getBird(req.getBirdId());

//        if (isBirdWorking(bird.getId())) {
//            throw new ProcessException("새가 현재 새장에 없습니다.");
//        }

        if (req.getToLandmarkId() != null && req.getToLandmarkId() != 0) {
            landmark = landmarkService.findById(req.getToLandmarkId());
            arrivalTime = locationDistanceService.getArrivalTime(departureTime, req.getLatitude().doubleValue(), req.getLongitude().doubleValue(),
                    landmark.getAddress().getLatitude().doubleValue(), landmark.getAddress().getLongitude().doubleValue(), bird.getHourly());
        } else if (req.getToPostBoxId() != null && req.getToPostBoxId() != 0) {
            postBox = postBoxService.findById(req.getToPostBoxId());
            toUser = postBox.getUser();
            arrivalTime = locationDistanceService.getArrivalTime(departureTime, req.getLatitude().doubleValue(), req.getLongitude().doubleValue(),
                    postBox.getAddress().getLatitude().doubleValue(), postBox.getAddress().getLongitude().doubleValue(), bird.getHourly());
        }else {
            throw new IllegalArgumentException("수신지 정보가 없습니다.");
        }

        LetterFont letterFont = getLetterFont(req.getFontId());
        LetterBackground letterBackground = getLetterBackground(req.getBackgroundId());

        Address address = Address.createAddress(req.getLatitude(), req.getLongitude(), null);

        return Letter.builder()
                .from(fromUser)
                .to(toUser)
                .bird(bird)
                .toLandmark(landmark)
                .toPostBox(postBox)
                .font(letterFont)
                .background(letterBackground)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .departureAddress(address)
                .content(req.getContent())
                .build();
    }

    private Boolean isBirdWorking(Long birdId) {
        return letterRepository.countByBirdWorking(birdId, LocalDateTime.now(Clock.systemUTC())) > 0;
    }

    private LetterBackground getLetterBackground(long id) {
        return letterBackgroundRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 편지배경입니다."));
    }

    private LetterFont getLetterFont(long id) {
        return letterFontRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 편지폰트입니다."));
    }

}

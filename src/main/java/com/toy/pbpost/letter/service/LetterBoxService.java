package com.toy.pbpost.letter.service;

import com.toy.pbpost.common.exception.ProcessException;
import com.toy.pbpost.letter.domain.Letter;
import com.toy.pbpost.letter.domain.LetterBox;
import com.toy.pbpost.letter.dto.LetterBoxDto;
import com.toy.pbpost.letter.repository.LetterBoxRepository;
import com.toy.pbpost.postbox.service.LandmarkLimitService;
import com.toy.pbpost.user.domain.User;
import com.toy.pbpost.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LetterBoxService {

    private final LetterBoxRepository letterBoxRepository;
    private final UserService userService;
    private final LandmarkLimitService landmarkLimitService;
    private final LetterService letterService;

    /*
    TODO
         랜드마크 거리 체크 ? (프론트에서 할지 , 백에서 할지)
         보관함 기능 정의 필요 (분류 등)
         랜드마크 편지 수령 제한은 랜드마크 마다인지 전체인지
     */
    @Transactional
    public LetterBoxDto.Res receiptLetter(String uid, Long letterId) {

        User user = userService.getUser(uid);

        LetterBox letterBox = getLetterBox(user);
        Letter letter = letterService.getLetter(letterId);

        if (letter.isLandmarkLetter()) {
            landmarkLimitService.addCount(uid, user.getTimezone());
        }else {
            if(!letter.getTo().equals(user)) {
                throw new ProcessException("나에게 온 편지가 아닙니다.");
            }
        }

        if (letter.getLetterBox() != null) {
            throw new ProcessException("이미 수령 된 편지입니다.");
        }

        letterBox.receiptLetter(letter);
        letter.setLetterBox(letterBox);

        return LetterBoxDto.Res.of(letterBox);
    }

    public LetterBoxDto.Res getLetterBoxDto(String uid) {
        User user = userService.getUser(uid);
        return LetterBoxDto.Res.of(getLetterBox(user));
    }

    public LetterBox getLetterBox(User user) {
        return letterBoxRepository.findByUserUid(user.getUid()).orElseGet(() -> letterBoxRepository.save(LetterBox.builder().user(user).build()));
    }

}

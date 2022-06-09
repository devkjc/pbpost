package com.toy.pbpost.addressBook.service;

import com.toy.pbpost.addressBook.domain.AddressBook;
import com.toy.pbpost.addressBook.dto.AddressBookDto;
import com.toy.pbpost.addressBook.repository.AddressBookRepository;
import com.toy.pbpost.postbox.domain.PostBox;
import com.toy.pbpost.postbox.dto.PostBoxDto;
import com.toy.pbpost.postbox.repository.PostBoxRepository;
import com.toy.pbpost.postbox.service.PostBoxService;
import com.toy.pbpost.user.domain.User;
import com.toy.pbpost.user.repository.UserRepository;
import com.toy.pbpost.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.pbpost.common.util.GeometryUtil.getLineString;

@Service
@RequiredArgsConstructor
public class AddressBookService {

    private final AddressBookRepository addressBookRepository;
    private final PostBoxRepository postBoxRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PostBoxService postBoxService;

    @Transactional
    public AddressBookDto.Res saveAddressBookByCode(String uid, String code) {

        User user = userService.getUser(uid);
        User targetUser = userRepository.findTopByCodeAndUidNot(code, uid).orElseThrow(() -> new IllegalArgumentException("유효한 코드가 아닙니다."));

        return saveAddressBook(user, targetUser.getUid());
    }

    public List<AddressBookDto.Res> getMyAddressBook(String uid) {
        return addressBookRepository.findByUserUid(uid).stream().map(AddressBookDto.Res::of).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAddressBook(String uid, long addressBookId) {
        addressBookRepository.deleteByIdAndUserUid(addressBookId, uid);
    }

    @Transactional(readOnly = true)
    public List<PostBoxDto.Res> getSquareMapPostBoxList(double baseLatitude, double baseLongitude, double distance, String uid) {

        String lineString = getLineString(baseLatitude, baseLongitude, distance);

        List<PostBox> squareMapPostBoxList = postBoxRepository.getSquareMapMyAddressBook(lineString, uid);

        return squareMapPostBoxList.stream().map(PostBoxDto.Res::of).collect(Collectors.toList());
    }

    @Transactional
    public AddressBookDto.Res saveAddressBookByNickname(String uid, String nickname) {

        User user = userService.getUser(uid);
        User targetUser = userRepository.findTopByNickNameAndUidNot(nickname, uid).orElseThrow(() -> new IllegalArgumentException("유효한 닉네임이 아닙니다."));

        return saveAddressBook(user, targetUser.getUid());
    }

    private AddressBookDto.Res saveAddressBook(User me, String targetUid) {
        PostBox postBox = postBoxService.getPostBox(targetUid).orElseThrow(() -> new IllegalArgumentException("우체통이 존재하지 않습니다."));
        AddressBook entityAddressBook = addressBookRepository.findTopByUserUidAndPostBoxId(me.getUid(), postBox.getId()).orElseGet(() -> {
            AddressBook addressBook = AddressBook.builder()
                    .postBox(postBox)
                    .user(me).build();
            return addressBookRepository.save(addressBook);
        });

        return AddressBookDto.Res.of(entityAddressBook);
    }
}

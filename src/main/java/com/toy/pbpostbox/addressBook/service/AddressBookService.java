package com.toy.pbpostbox.addressBook.service;

import com.toy.pbpostbox.addressBook.domain.AddressBook;
import com.toy.pbpostbox.addressBook.dto.AddressBookDto;
import com.toy.pbpostbox.addressBook.repository.AddressBookRepository;
import com.toy.pbpostbox.common.exception.ProcessException;
import com.toy.pbpostbox.postbox.domain.PostBox;
import com.toy.pbpostbox.postbox.dto.PostBoxDto;
import com.toy.pbpostbox.postbox.repository.PostBoxRepository;
import com.toy.pbpostbox.postbox.service.PostBoxService;
import com.toy.pbpostbox.user.domain.User;
import com.toy.pbpostbox.user.repository.UserRepository;
import com.toy.pbpostbox.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.pbpostbox.common.util.GeometryUtil.getLineString;

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
        User codeUser = userRepository.findTopByCodeAndUidNot(code, uid).orElseThrow(() -> new ProcessException("유효한 코드가 아닙니다."));
        PostBox postBox = postBoxService.getPostBox(codeUser.getUid()).orElseThrow(() -> new ProcessException("유효한 코드가 아닙니다."));

        AddressBook entityAddressBook = addressBookRepository.findTopByUserUidAndPostBoxId(user.getUid(), postBox.getId()).orElseGet(() -> {
            AddressBook addressBook = AddressBook.builder()
                    .postBox(postBox)
                    .user(user).build();
            return addressBookRepository.save(addressBook);
        });

        return AddressBookDto.Res.of(entityAddressBook);
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
}

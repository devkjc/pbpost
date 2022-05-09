package com.toy.pbpostbox.addressBook.service;

import com.toy.pbpostbox.addressBook.domain.AddressBook;
import com.toy.pbpostbox.addressBook.dto.AddressBookDto;
import com.toy.pbpostbox.addressBook.repository.AddressBookRepository;
import com.toy.pbpostbox.common.exception.ProcessException;
import com.toy.pbpostbox.postbox.domain.PostBox;
import com.toy.pbpostbox.postbox.service.PostBoxService;
import com.toy.pbpostbox.user.domain.User;
import com.toy.pbpostbox.user.repository.UserRepository;
import com.toy.pbpostbox.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressBookService {

    private final AddressBookRepository addressBookRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PostBoxService postBoxService;

    @Transactional
    public AddressBookDto.Res saveAddressBookByCode(String uid, String code) {

        User user = userService.getUser(uid);
        User codeUser = userRepository.findTopByCode(code).orElseThrow(() -> new ProcessException("유효한 코드가 아닙니다."));
        PostBox postBox = postBoxService.getPostBox(codeUser.getUid()).orElseThrow(() -> new ProcessException("유효한 코드가 아닙니다."));

        AddressBook addressBook = AddressBook.builder()
                .postBox(postBox)
                .user(user).build();

        return AddressBookDto.Res.of(addressBookRepository.save(addressBook));
    }

    public List<AddressBookDto.Res> getMyAddressBook(String uid) {
        return addressBookRepository.findByUserUid(uid).stream().map(AddressBookDto.Res::of).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAddressBook(String uid, long addressBookId) {
        addressBookRepository.deleteByIdAndUserUid(addressBookId, uid);
    }
}

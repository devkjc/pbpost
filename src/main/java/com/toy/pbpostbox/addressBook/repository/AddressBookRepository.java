package com.toy.pbpostbox.addressBook.repository;

import com.toy.pbpostbox.addressBook.domain.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    List<AddressBook> findByUserUid(String user_uid);

    void deleteByIdAndUserUid(long id, String user_uid);

}

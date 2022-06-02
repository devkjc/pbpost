package com.toy.pbpost.addressBook.repository;

import com.toy.pbpost.addressBook.domain.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    List<AddressBook> findByUserUid(String user_uid);

    Optional<AddressBook> findTopByUserUidAndPostBoxId(String user_uid, long postBox_id);

    void deleteByIdAndUserUid(long id, String user_uid);

}

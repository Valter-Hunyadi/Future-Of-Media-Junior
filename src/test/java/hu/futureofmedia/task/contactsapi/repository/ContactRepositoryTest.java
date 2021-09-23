package hu.futureofmedia.task.contactsapi.repository;

import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.Status;
import hu.futureofmedia.task.contactsapi.repositories.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void saveOne() {
        Contact mattiaCorvino = Contact.builder()
                .firstName("Mátyás")
                .lastName("Hunyadi")
                .lastUpdatedDate(LocalDateTime.now())
                .creationDate(LocalDateTime.now())
                .email("matyas@king.hu")
                .status(Status.ACTIVE)
                .build();

        contactRepository.save(mattiaCorvino);

        List<Contact> contactList = contactRepository.findAll();

        assertThat(contactList).hasSize(1).anyMatch(contact -> contact.getFirstName().equals("Mátyás"));
    }
}

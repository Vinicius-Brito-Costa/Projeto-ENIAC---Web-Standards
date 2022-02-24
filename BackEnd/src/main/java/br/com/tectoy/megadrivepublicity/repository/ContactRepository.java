package br.com.tectoy.megadrivepublicity.repository;

import br.com.tectoy.megadrivepublicity.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}

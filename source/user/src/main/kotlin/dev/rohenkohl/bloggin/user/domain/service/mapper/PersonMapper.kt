package dev.rohenkohl.bloggin.user.domain.service.mapper

import dev.rohenkohl.bloggin.user.domain.model.Person
import dev.rohenkohl.bloggin.user.domain.model.repository.PersonRepository
import dev.rohenkohl.bloggin.user.domain.service.transfer.PersonTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Loader
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class PersonMapper() : Exporter<PersonTransfer, Person>, Importer<PersonTransfer, Person>, Loader<Person>, Modifier<PersonTransfer, Person> {

    private lateinit var personRepository: PersonRepository

    @Inject
    internal constructor(personRepository: PersonRepository): this() {
        this.personRepository = personRepository
    }

    override fun export(identifiable: Person): Content<PersonTransfer> {
        val personTransfer = PersonTransfer(identifiable.mail, identifiable.nickname)

        return Content(personTransfer, identifiable.uuid)
    }

    override fun import(transfer: PersonTransfer): Person {
        return Person(transfer.mail.nonnull(), transfer.nickname.nonnull()) pipe personRepository::create
    }

    override fun load(reference: Reference<Person>): Person {
        return personRepository.readByUUID(reference.uuid)
    }

    override fun modify(content: Content<PersonTransfer>): Reference<Person> {
        val person = personRepository.readByUUID(content.uuid)

        person.nickname = content.payload.nickname ?: person.nickname

        return Reference(person)
    }
}
package dev.rohenkohl.bloggin.person.domain.service.mapper

import dev.rohenkohl.bloggin.person.domain.model.Person
import dev.rohenkohl.bloggin.person.domain.repository.PersonRepository
import dev.rohenkohl.bloggin.person.domain.service.transfer.PersonDTO
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Loader
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnullName
import dev.rohenkohl.bloggin.zero.extension.nullableName
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class PersonMapper : Exporter<PersonDTO, Person>, Importer<PersonDTO, Person>, Loader<Person>,
    Modifier<PersonDTO, Person> {

    @Inject
    private lateinit var personRepository: PersonRepository

    override fun export(identifiable: Person): Content<PersonDTO> {
        val personDTO = PersonDTO(identifiable.nickname.value, identifiable.username.value)

        return Reference(personDTO, identifiable.uuid)
    }

    override fun import(dto: PersonDTO): Person {
        val person = Person(dto.nickname.nonnullName(), dto.username.nonnullName())

        return personRepository.create(person)
    }

    override fun load(reference: Reference<Person>): Person {
        return personRepository.readByUUID(reference.uuid)
    }

    override fun modify(content: Content<PersonDTO>): Reference<Person> {
        val person = personRepository.readByUUID(content.uuid)

        person.nickname = content.payload.nickname.nullableName() ?: person.nickname
        person.username = content.payload.username.nullableName() ?: person.username

        return Reference(person)
    }
}
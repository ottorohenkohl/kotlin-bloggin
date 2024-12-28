package de.ottorohenkohl.bloggin.person.domain.service.mapper

import de.ottorohenkohl.bloggin.person.domain.service.transfer.PersonDTO
import de.ottorohenkohl.bloggin.person.domain.model.Person
import de.ottorohenkohl.bloggin.person.domain.repository.PersonRepository
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Exporter
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Importer
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Loader
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Modifier
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.nonnullName
import de.ottorohenkohl.bloggin.zero.extension.nullableName
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
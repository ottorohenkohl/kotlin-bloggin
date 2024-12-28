package de.ottorohenkohl.bloggin.person.domain.service

import de.ottorohenkohl.bloggin.person.domain.service.mapper.PersonMapper
import de.ottorohenkohl.bloggin.person.domain.service.transfer.PersonDTO
import de.ottorohenkohl.bloggin.person.domain.model.Person
import de.ottorohenkohl.bloggin.person.domain.repository.PersonRepository
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Range
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Range.Excerpt
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.yield
import de.ottorohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class PersonService {

    @Inject
    private lateinit var personMapper: PersonMapper

    @Inject
    private lateinit var personRepository: PersonRepository

    @Transactional
    fun change(content: Content<PersonDTO>): Reference<Person> {
        return personMapper.modify(content)
    }

    @Transactional
    fun erase(reference: Reference<Person>): Reference<Person> {
        return personRepository.readByUUID(reference.uuid) pipe personRepository::delete yield reference
    }

    @Transactional
    fun find(range: Range<PersonDTO>): Excerpt<PersonDTO> {
        return personRepository.readByPage(range.page()).map(personMapper::export) pipe range::fill
    }

    @Transactional
    fun find(reference: Reference<Person>): Content<PersonDTO> {
        return personRepository.readByUUID(reference.uuid) pipe personMapper::export
    }

    @Transactional
    fun store(personDTO: PersonDTO): Content<PersonDTO> {
        return personMapper.import(personDTO) pipe personMapper::export
    }
}
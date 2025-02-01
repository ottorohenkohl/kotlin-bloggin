package dev.rohenkohl.bloggin.person.domain.service

import dev.rohenkohl.bloggin.person.domain.annotation.value.Role
import dev.rohenkohl.bloggin.person.domain.model.Permission
import dev.rohenkohl.bloggin.person.domain.model.Person
import dev.rohenkohl.bloggin.person.domain.repository.PermissionRepository
import dev.rohenkohl.bloggin.person.domain.repository.PersonRepository
import dev.rohenkohl.bloggin.person.domain.service.mapper.PermissionMapper
import dev.rohenkohl.bloggin.person.domain.service.mapper.PersonMapper
import dev.rohenkohl.bloggin.person.domain.service.transfer.PersonDTO
import dev.rohenkohl.bloggin.person.extension.PersonExistsException
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Range
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Range.Excerpt
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.*
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.NoResultException
import jakarta.transaction.Transactional

@ApplicationScoped
class PersonService {

    @Inject
    private lateinit var permissionRepository: PermissionRepository

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
        val person = fail<NoResultException>({ personRepository.readByUsername(personDTO.username.nonnullName()) }, PersonExistsException()) yield personMapper.import(personDTO)

        return permissionRepository.create(Permission(Role.AUTHOR, person)) yield person pipe personMapper::export
    }
}
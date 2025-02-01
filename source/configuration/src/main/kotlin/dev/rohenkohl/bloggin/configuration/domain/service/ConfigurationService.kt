package dev.rohenkohl.bloggin.configuration.domain.service

import dev.rohenkohl.bloggin.configuration.domain.model.Configuration
import dev.rohenkohl.bloggin.configuration.domain.repository.ConfigurationRepository
import dev.rohenkohl.bloggin.configuration.domain.service.mapper.ConfigurationMapper
import dev.rohenkohl.bloggin.configuration.domain.service.transfer.ConfigurationDTO
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@RequestScoped
class ConfigurationService {

    @Inject
    private lateinit var configurationMapper: ConfigurationMapper

    @Inject
    private lateinit var configurationRepository: ConfigurationRepository

    @Transactional
    fun change(content: Content<ConfigurationDTO>): Reference<Configuration> {
        return configurationMapper.modify(content)
    }

    @Transactional
    fun find(): Content<ConfigurationDTO> {
        return configurationRepository.read() pipe configurationMapper::export
    }
}
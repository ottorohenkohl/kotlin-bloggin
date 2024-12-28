package de.ottorohenkohl.bloggin.settings.domain.service

import de.ottorohenkohl.bloggin.settings.domain.model.Configuration
import de.ottorohenkohl.bloggin.settings.domain.repository.ConfigurationRepository
import de.ottorohenkohl.bloggin.settings.domain.service.mapper.ConfigurationMapper
import de.ottorohenkohl.bloggin.settings.domain.service.transfer.ConfigurationDTO
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.pipe
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
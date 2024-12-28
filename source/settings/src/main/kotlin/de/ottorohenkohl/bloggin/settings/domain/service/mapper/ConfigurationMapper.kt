package de.ottorohenkohl.bloggin.settings.domain.service.mapper

import de.ottorohenkohl.bloggin.settings.domain.model.Configuration
import de.ottorohenkohl.bloggin.settings.domain.service.transfer.ConfigurationDTO
import de.ottorohenkohl.bloggin.settings.domain.repository.ConfigurationRepository
import de.ottorohenkohl.bloggin.settings.extension.nullableTitle
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Exporter
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Modifier
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.nullableDescription
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class ConfigurationMapper : Exporter<ConfigurationDTO, Configuration>, Modifier<ConfigurationDTO, Configuration> {

    @Inject
    private lateinit var configurationRepository: ConfigurationRepository

    override fun export(identifiable: Configuration): Content<ConfigurationDTO> {
        val configurationDTO = ConfigurationDTO(identifiable.description.value, identifiable.title.value)

        return Reference(configurationDTO, identifiable.uuid)
    }

    override fun modify(content: Content<ConfigurationDTO>): Reference<Configuration> {
        val configuration = configurationRepository.readByUUID(content.uuid)

        configuration.description = content.payload.description.nullableDescription() ?: configuration.description
        configuration.title = content.payload.title.nullableTitle() ?: configuration.title

        return Reference(configuration)
    }
}
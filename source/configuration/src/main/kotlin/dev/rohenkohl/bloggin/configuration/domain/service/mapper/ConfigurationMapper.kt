package dev.rohenkohl.bloggin.configuration.domain.service.mapper

import dev.rohenkohl.bloggin.configuration.domain.model.Configuration
import dev.rohenkohl.bloggin.configuration.domain.repository.ConfigurationRepository
import dev.rohenkohl.bloggin.configuration.domain.service.transfer.ConfigurationDTO
import dev.rohenkohl.bloggin.configuration.extension.nullableTitle
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nullableDescription
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
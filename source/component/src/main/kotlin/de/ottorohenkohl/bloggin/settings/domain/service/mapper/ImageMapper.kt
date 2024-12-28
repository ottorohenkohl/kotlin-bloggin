package de.ottorohenkohl.bloggin.settings.domain.service.mapper

import de.ottorohenkohl.bloggin.settings.domain.service.transfer.ImageDTO
import de.ottorohenkohl.bloggin.settings.domain.model.Image
import de.ottorohenkohl.bloggin.settings.domain.repository.ImageRepository
import de.ottorohenkohl.bloggin.settings.extension.nonnullData
import de.ottorohenkohl.bloggin.settings.extension.nonnullMimetype
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Exporter
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Importer
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class ImageMapper : Exporter<ImageDTO, Image>, Importer<ImageDTO, Image> {

    @Inject
    private lateinit var ImageRepository: ImageRepository

    override fun export(identifiable: Image): Content<ImageDTO> {
        val imageDTO = ImageDTO(identifiable.data.value, identifiable.mimetype.value)

        return Reference(imageDTO, identifiable.uuid)
    }

    override fun import(dto: ImageDTO): Image {
        val image = Image(dto.data.nonnullData(), dto.mimetype.nonnullMimetype())

        return ImageRepository.create(image)
    }
}
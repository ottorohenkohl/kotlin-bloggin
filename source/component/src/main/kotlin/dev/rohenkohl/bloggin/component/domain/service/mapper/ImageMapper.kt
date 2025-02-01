package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Image
import dev.rohenkohl.bloggin.component.domain.repository.ImageRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.ImageDTO
import dev.rohenkohl.bloggin.component.extension.nonnullData
import dev.rohenkohl.bloggin.component.extension.nonnullMimetype
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
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
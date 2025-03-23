package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Image
import dev.rohenkohl.bloggin.component.domain.model.repository.ImageRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.ImageTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class ImageMapper(): Exporter<ImageTransfer, Image>, Importer<ImageTransfer, Image> {

    private lateinit var imageRepository: ImageRepository

    @Inject
    internal constructor(imageRepository: ImageRepository) : this() {
        this.imageRepository = imageRepository
    }

    override fun export(identifiable: Image): Content<ImageTransfer> {
        val imageTransfer = ImageTransfer(identifiable.mimetype, identifiable.data)

        return Content(imageTransfer, identifiable.uuid)
    }

    override fun import(transfer: ImageTransfer): Image {
        val image = Image(transfer.mimetype.nonnull(), transfer.data.nonnull())

        return imageRepository.create(image)
    }
}
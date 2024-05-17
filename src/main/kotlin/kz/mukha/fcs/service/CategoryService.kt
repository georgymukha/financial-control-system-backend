package kz.mukha.fcs.service

import kz.mukha.fcs.model.Category
import kz.mukha.fcs.repository.CategoryRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun createCategory(name: String, isCustom: Boolean = true): Category {
        val category = Category(name = name, isCustom = isCustom)
        return categoryRepository.save(category)
    }

    fun getCategoryById(id: UUID): Category? {
        return categoryRepository.findById(id).orElse(null)
    }

    fun deleteCategory(id: UUID) {
        categoryRepository.deleteById(id)
    }
}
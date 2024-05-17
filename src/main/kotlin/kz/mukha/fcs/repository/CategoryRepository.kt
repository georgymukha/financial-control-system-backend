package kz.mukha.fcs.repository

import kz.mukha.fcs.model.Account
import kz.mukha.fcs.model.Category
import kz.mukha.fcs.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CategoryRepository : JpaRepository<Category, UUID>
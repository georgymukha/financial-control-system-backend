package kz.mukha.fcs.repository

import kz.mukha.fcs.model.Account
import kz.mukha.fcs.model.TransactionItem
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TransactionItemRepository : JpaRepository<TransactionItem, UUID>
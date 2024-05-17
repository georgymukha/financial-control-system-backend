package kz.mukha.fcs.repository

import kz.mukha.fcs.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AccountRepository : JpaRepository<Account, UUID>
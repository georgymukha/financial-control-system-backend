package kz.mukha.fcs.repository

import kz.mukha.fcs.model.User
import kz.mukha.fcs.model.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface WalletRepository : JpaRepository<Wallet, UUID>
package kz.mukha.fcs.service

import kz.mukha.fcs.model.User
import kz.mukha.fcs.model.Wallet
import kz.mukha.fcs.repository.WalletRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class WalletService(
    private val walletRepository: WalletRepository,
) {

    fun createWallet(name: String, owner: User): Wallet {
        val wallet = Wallet(name = name, owner = owner)
        return walletRepository.save(wallet)
    }

    fun getWalletById(id: UUID): Wallet? {
        return walletRepository.findById(id).orElse(null)
    }

    fun deleteWallet(id: UUID) {
        walletRepository.deleteById(id)
    }
}
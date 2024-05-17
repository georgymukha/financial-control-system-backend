package kz.mukha.fcs.service

import kz.mukha.fcs.model.AccessLevel
import kz.mukha.fcs.model.AccessRights
import kz.mukha.fcs.model.User
import kz.mukha.fcs.model.Wallet
import kz.mukha.fcs.repository.AccessRightsRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AccessRightsService(
    private val accessRightsRepository: AccessRightsRepository,
) {

    fun grantAccess(wallet: Wallet, user: User, accessLevel: AccessLevel): AccessRights {
        val accessRights = AccessRights(wallet = wallet, user = user, accessLevel = accessLevel)
        return accessRightsRepository.save(accessRights)
    }

    fun getAccessRightsById(id: UUID): AccessRights? {
        return accessRightsRepository.findById(id).orElse(null)
    }

    fun revokeAccess(id: UUID) {
        accessRightsRepository.deleteById(id)
    }
}
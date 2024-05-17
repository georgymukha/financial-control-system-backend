package kz.mukha.fcs.repository

import kz.mukha.fcs.model.AccessRights
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AccessRightsRepository : JpaRepository<AccessRights, UUID>
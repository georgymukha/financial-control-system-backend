package kz.mukha.fcs.controller

import kz.mukha.fcs.dto.LoginRequest
import kz.mukha.fcs.dto.LoginResponse
import kz.mukha.fcs.security.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
) {

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.username,
                loginRequest.password
            )
        )
        val userDetails = authentication.principal as UserDetails
        val token = jwtUtil.generateToken(userDetails)
        return ResponseEntity.ok(LoginResponse(token))

    }
}
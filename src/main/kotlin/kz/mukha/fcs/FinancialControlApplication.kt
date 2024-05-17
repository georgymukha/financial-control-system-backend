package kz.mukha.fcs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FinancialControlApplication

fun main(args: Array<String>) {
    runApplication<FinancialControlApplication>(*args)
}
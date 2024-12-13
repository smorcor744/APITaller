package com.example.APITaller

import com.example.APITaller.security.RSAKeysProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RSAKeysProperties::class)
class ApiTallerApplication

fun main(args: Array<String>) {
	runApplication<ApiTallerApplication>(*args)
}

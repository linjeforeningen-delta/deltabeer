package no.deltahouse.kotlinbeer.model.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Wallet does not belong to user")
class WalletUserMismatchException : RuntimeException() {
}
package com.claramente.core.network.http

import java.io.IOException

class ApiException(val status: Int, message: String) : IOException(message)

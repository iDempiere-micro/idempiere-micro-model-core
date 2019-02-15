package org.compiere.util

import org.idempiere.common.exceptions.AdempiereException
import org.idempiere.common.util.CLogger

private const val unknownError = "UnknownError"

private fun getMessageFromLogger(): String {
    val err = CLogger.retrieveError()
    return err?.name ?: unknownError
}

open class AdempiereExceptionEx : AdempiereException {
    constructor() : super(getMessageFromLogger())
    constructor(msg: String) : super(msg)
    constructor(cause: Throwable) : super(cause)
    constructor(msg: String, cause: Throwable) : super(msg, cause)
}
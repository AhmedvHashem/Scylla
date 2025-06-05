package com.hashem.mediapicker

import android.net.Uri

/**
 * Represents the result of a media picking or capturing operation.
 */
sealed class MediaPickerResult {
    /**
     * Indicates successful selection or capture of a single item.
     * @param uri The URI of the selected or captured item.
     */
    data class SuccessSingle(val uri: Uri) : MediaPickerResult()

    /**
     * Indicates successful selection of multiple items.
     * @param uris A list of URIs for the selected items.
     */
    data class SuccessMultiple(val uris: List<Uri>) : MediaPickerResult()

    /**
     * Indicates that the operation was cancelled by the user.
     */
    data object Cancelled : MediaPickerResult()

    /**
     * Indicates an error occurred during the operation.
     * @param message A descriptive error message.
     * @param exception (Optional) The underlying exception, if available.
     */
    data class Error(val message: String, val exception: Throwable? = null) : MediaPickerResult()
}


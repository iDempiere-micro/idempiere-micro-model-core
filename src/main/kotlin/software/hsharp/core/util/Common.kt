package software.hsharp.core.util

fun String.toNullIfEmpty(): String? {
    return if (this.isEmpty()) null else this
}

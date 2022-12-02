package demo.lets.work.newsapplication.core.utils

sealed class Resource<T>(val data: T?, val message: String?) {
    class Loading<T>(data: T? = null) : Resource<T>(data, null)
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(data: T?, message: String) : Resource<T>(null, message)
}

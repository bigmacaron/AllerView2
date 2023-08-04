package kr.kro.fatcats.allerview.model.local

@JvmInline
value class Duration private constructor(
    val millis: Long,
) {
    companion object {
        private const val BASE_SECOND = 1000L
        fun millis(millis: Long) = Duration(millis)
        fun seconds(seconds: Long): Long = Duration(seconds * BASE_SECOND).millis
    }
}
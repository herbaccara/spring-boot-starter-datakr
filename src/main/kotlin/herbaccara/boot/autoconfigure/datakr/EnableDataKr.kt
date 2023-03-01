package herbaccara.boot.autoconfigure.datakr

import org.springframework.context.annotation.Import
import java.lang.annotation.*

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Import(DataKrAutoConfiguration::class)
annotation class EnableDataKr

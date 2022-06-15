package wtf.shield.util

import org.reflections.Reflections
import kotlin.reflect.KClass

val Class<*>.toKotlin get() = if (getAnnotation(Metadata::class.java)?.kind == 1) kotlin else null

object ReflectionUtil {
    fun classesFrom(pkg: String, klass: KClass<*>): List<KClass<out Any>> =
        Reflections(pkg).getSubTypesOf(klass.java).mapNotNull { it.toKotlin }
}
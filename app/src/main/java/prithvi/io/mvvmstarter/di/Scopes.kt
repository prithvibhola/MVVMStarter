package prithvi.io.mvvmstarter.di

import javax.inject.Scope

@MustBeDocumented
@Scope
@Retention
annotation class ActivityScoped

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS,
        AnnotationTarget.FILE,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
annotation class FragmentScoped

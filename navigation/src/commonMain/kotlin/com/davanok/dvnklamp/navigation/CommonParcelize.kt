package com.davanok.dvnklamp.navigation

@OptIn(ExperimentalMultiplatform::class)
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
@OptionalExpectation
expect annotation class CommonParcelize()

expect interface CommonParcelable
package com.giuseppesorce.architecture

import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope


@Scope
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class QuizScope
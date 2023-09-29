package com.aatec.quiztime.utils;

public interface EntityMapper<F, T> {
    T mapFromEntity(F entity);

    F mapToEntity(T domainModel);
}

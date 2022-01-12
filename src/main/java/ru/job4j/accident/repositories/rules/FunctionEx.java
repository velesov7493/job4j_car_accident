package ru.job4j.accident.repositories.rules;

@FunctionalInterface
public interface FunctionEx<T, R> {
	R apply(T argument) throws Exception;
}

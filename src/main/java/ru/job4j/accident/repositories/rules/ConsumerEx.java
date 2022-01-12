package ru.job4j.accident.repositories.rules;

@FunctionalInterface
public interface ConsumerEx<T> {
	void accept(T argument) throws Exception;
}

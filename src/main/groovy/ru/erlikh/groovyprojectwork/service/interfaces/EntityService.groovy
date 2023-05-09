package ru.erlikh.groovyprojectwork.service.interfaces

interface EntityService<T> {

    T findById(Long id)

    List<T> findAll()

    T save(T entity)

    List<T> saveAll(List<T> entities)

    void delete(T entity)

    void updateData()
}
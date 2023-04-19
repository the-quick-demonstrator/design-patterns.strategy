package com.github.curriculeon;

import java.io.Serializable;

public class RepositoryLogger<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>>
        implements RepositoryLoggerInterface<IdType, EntityType> {
    private RepositoryInterface<IdType, EntityType> repository;

    public RepositoryLogger(RepositoryInterface<IdType, EntityType> repository) {
        this.repository = repository;
    }

    @Override
    public RepositoryInterface<IdType, EntityType> getRepository() {
        return repository;
    }
}

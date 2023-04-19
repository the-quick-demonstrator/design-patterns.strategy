package com.github.curriculeon;

import java.io.Serializable;
import java.util.List;

public interface RepositoryInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>> {
    List<EntityType> getEntityList();

    default EntityType add(EntityType person) {
        if (findById(person.getId()) != null) {
            throw new IllegalArgumentException();
        }
        getEntityList().add(person);
        return findById(person.getId());
    }

    default EntityType findById(IdType id) {
        return getEntityList()
                .stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    default EntityType updateById(IdType id, EntityType newEntityTypeData) {
        final EntityType personToUpdate = findById(id);
        final int indexToSet = getEntityList().indexOf(personToUpdate);
        getEntityList().set(indexToSet, newEntityTypeData);
        return getEntityList().get(indexToSet);
    }

    default EntityType deleteById(IdType id) {
        final EntityType personToDelete = findById(id);
        final int indexToDelete = getEntityList().indexOf(personToDelete);
        getEntityList().remove(indexToDelete);
        return personToDelete;
    }

    default EntityType delete(EntityType personToDelete) {
        getEntityList().remove(personToDelete);
        return personToDelete;
    }
}

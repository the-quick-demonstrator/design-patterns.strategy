package com.github.curriculeon;

import java.io.Serializable;
import java.util.List;
import java.util.function.BiFunction;
import java.util.logging.Logger;

public interface RepositoryLoggerInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>>
        extends RepositoryInterface<IdType, EntityType> {

    RepositoryInterface<IdType, EntityType> getRepository();

    default Logger getLogger() {
        return Logger.getLogger(getClass().getSimpleName());
    }

    default EntityType logMethod(String methodName, BiFunction<IdType, EntityType, EntityType> method, IdType id, EntityType entity) {
        final String attemptMessage = "Attempting to perform `%s(%s, %s)`...";
        final String failureMessage = "Failed to perform `%s(%s, %s)`!";
        final String successMessage = "Successfully performed `%s(%s, %s)` with result of %s!";
        try {
            getLogger().info(String.format(attemptMessage, methodName, id, entity));
            EntityType result = method.apply(id, entity);
            getLogger().info(String.format(successMessage, methodName, id, entity, result));
            return result;
        } catch (Exception e) {
            getLogger().info(String.format(failureMessage, methodName, id, entity));
            e.printStackTrace();
            return null;
        }
    }

    default List<EntityType> getEntityList() {
        return getRepository().getEntityList();
    }

    default EntityType add(EntityType person) {
        final String methodName = "add";
        final BiFunction<IdType, EntityType, EntityType> method = (id, entity) -> getRepository().add(entity);
        return logMethod(methodName, method, null, person);
    }

    default EntityType findById(IdType id) {
        final String methodName = "findById";
        final BiFunction<IdType, EntityType, EntityType> method = (entityId, entity) -> getRepository().findById(entityId);
        return logMethod(methodName, method, id, null);
    }

    default EntityType updateById(IdType id, EntityType newEntityTypeData) {
        final String methodName = "updateById";
        return logMethod(methodName, getRepository()::updateById, id, newEntityTypeData);
    }

    default EntityType deleteById(IdType id) {
        final String methodName = "deleteById";
        final BiFunction<IdType, EntityType, EntityType> method = (entityId, entity) -> getRepository().deleteById(entityId);
        return logMethod(methodName, method, id, null);
    }

    default EntityType delete(EntityType personToDelete) {
        final String methodName = "delete";
        final BiFunction<IdType, EntityType, EntityType> method = (entityId, entity) -> getRepository().delete(entity);
        return logMethod(methodName, method, null, personToDelete);
    }
}

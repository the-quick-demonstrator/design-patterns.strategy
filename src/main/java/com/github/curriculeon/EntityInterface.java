package com.github.curriculeon;

import java.io.Serializable;

public interface EntityInterface<IdType extends Serializable> {
    IdType getId();
}

/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.language.base.internal.model;

import org.gradle.language.base.FunctionalSourceSet;
import org.gradle.model.internal.manage.schema.ModelSchemaStore;
import org.gradle.model.internal.manage.schema.extract.ModelSchemaExtractionContext;
import org.gradle.model.internal.manage.schema.extract.ModelSchemaExtractionStrategy;
import org.gradle.model.internal.type.ModelType;

public class FunctionalSourceSetSchemaExtractionStrategy implements ModelSchemaExtractionStrategy {
    @Override
    public <T> void extract(ModelSchemaExtractionContext<T> extractionContext, ModelSchemaStore store) {
        ModelType<FunctionalSourceSet> functionalSourceSetModelType = ModelType.of(FunctionalSourceSet.class);
        if (extractionContext.getType().isAssignableFrom(functionalSourceSetModelType)) {
            extractionContext.found(new FunctionalSourceSetSchema<T>(extractionContext.getType()));
        }
    }
}

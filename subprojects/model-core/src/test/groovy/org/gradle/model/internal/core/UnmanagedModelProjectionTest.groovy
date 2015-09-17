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

package org.gradle.model.internal.core

import org.gradle.model.internal.type.ModelType
import spock.lang.Specification

class UnmanagedModelProjectionTest extends Specification {
    def "describes available types"() {
        expect:
        def projection = UnmanagedModelProjection.of(type)
        projection.getReadableTypeDescriptions(Stub(MutableModelNode)) as List == [description]
        projection.getWritableTypeDescriptions(Stub(MutableModelNode)) as List == [description]

        where:
        type     | description
        String   | "java.lang.String (or assignment compatible type thereof)"
        Object   | "java.lang.Object"
        Runnable | "java.lang.Runnable"
    }

    def "has no writable types available when read-only"() {
        expect:
        def projection = new UnmanagedModelProjection(ModelType.of(String), true, false)
        !projection.canBeViewedAsWritable(ModelType.of(String))
        projection.getWritableTypeDescriptions(Stub(MutableModelNode)).empty
    }
}

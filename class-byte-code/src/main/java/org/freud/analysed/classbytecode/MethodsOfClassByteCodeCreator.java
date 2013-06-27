/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.freud.analysed.classbytecode;

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;
import org.freud.core.Creator;

public final class MethodsOfClassByteCodeCreator implements Creator<ClassByteCode, Iterable<ClassByteCodeMethod>> {
    @Override
    public Iterable<ClassByteCodeMethod> create(final ClassByteCode source) {
        return source.getMethodList();
    }
}
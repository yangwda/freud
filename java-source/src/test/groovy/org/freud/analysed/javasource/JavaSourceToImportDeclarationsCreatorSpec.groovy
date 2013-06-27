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



package org.freud.analysed.javasource

import spock.lang.Specification
import spock.lang.Subject

class JavaSourceToImportDeclarationsCreatorSpec extends Specification {

    @Subject
    JavaSourceToImportDeclarationsCreator creator = new JavaSourceToImportDeclarationsCreator()

    def 'returns import declarations for Java source'() {
    given:
        JavaSource javaSource = Mock()
        ImportDeclaration import1 = Mock()
        ImportDeclaration import2 = Mock()
        ImportDeclaration import3 = Mock()
        javaSource.importDeclarations >> [import1, import2, import3]
    expect:
        creator.create(javaSource) as List == [import1, import2, import3]
    }
}
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



package org.freud.analysed.properties

import spock.lang.Specification
import spock.lang.Subject

class PropertiesFromStringCreatorSpec extends Specification {

    @Subject
    PropertiesFromStringCreator creator = new PropertiesFromStringCreator()

    def 'build Properties from a String'() {
    when:
        Properties result = creator.create('a=b\nc=d\ne=f')
    then:
        result == [a: 'b', c: 'd', e: 'f'] as Properties
    }
}

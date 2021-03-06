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

package org.freud.java;

import org.freud.core.Creator;
import org.freud.core.Filter;
import org.freud.core.FreudSource;
import org.freud.core.iterator.AnalysedObjects;
import org.freud.core.iterator.Files;
import org.freud.core.iterator.FilteredAnalysedObjects;
import org.freud.core.iterator.SystemResources;
import org.freud.java.rule.FreudRuleBuilder;
import org.hamcrest.Matcher;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.freud.core.iterator.AnalysedObjectBreadcrumbs.BREADCRUMBS;

public final class Freud {


    public static <T> FreudRuleBuilder<T> iterateOver(Class<T> type) {
        return new FreudRuleBuilder<T>(type);
    }

    public static <A> boolean analyse(A analysedObject, Matcher assertion) {
        return assertion.matches(analysedObject);
    }

    public static <A> Iterable<Object[]> parameterise(Iterable<A> sourceObjects, int numberOfParameters) {
        return new AnalysedObjects<A, Object[]>(new ParametersCreator<A>(numberOfParameters), sourceObjects, false);
    }

    public static <A> Iterable<A> forEach(Iterable<A> analysedObjects, Matcher<A> matcher) {
        return new FilteredAnalysedObjects<A>(analysedObjects, new ToAcceptFilter<A>(matcher));
    }

    public static FreudSource<File> filesIn(String... paths) {
        return new FreudSource<File>(new Files(asList(paths), true, null), File.class);
    }

    public static FreudSource<File> filesIn(FilenameFilter filter, String... paths) {
        return new FreudSource<File>(new Files(asList(paths), true, filter), File.class);
    }

    public static FreudSource<URL> resourcesOf(Collection<String> paths) {
        return new FreudSource<URL>(new SystemResources(paths), URL.class);
    }

    public static FreudSource<URL> resourcesOf(Collection<String> paths, ClassLoader classLoader) {
        return new FreudSource<URL>(new SystemResources(paths, classLoader), URL.class);
    }


    public static FreudSource<File> nonRecursivelyFilesIn(String... paths) {
        return new FreudSource<File>(new Files(asList(paths), false, null), File.class);
    }

    public static FreudSource<File> nonRecursivelyFilesIn(FilenameFilter filter, String... paths) {
        return new FreudSource<File>(new Files(asList(paths), false, filter), File.class);
    }

    public static FreudSource<File> filesIn(File... files) {
        return new FreudSource<File>(new Files(asList(files), true, null), File.class);
    }

    public static FreudSource<File> nonRecursivelyFilesIn(File... files) {
        return new FreudSource<File>(new Files(asList(files), false, null), File.class);
    }

    public static FreudSource<File> filesIn(FilenameFilter filter, File... files) {
        return new FreudSource<File>(new Files(asList(files), true, filter), File.class);
    }

    public static FreudSource<File> nonRecursivelyFilesIn(FilenameFilter filter, File... files) {
        return new FreudSource<File>(new Files(asList(files), false, filter), File.class);
    }

    private static final class ToAcceptFilter<A> implements Filter<A> {

        private final Matcher<A> matcher;

        private ToAcceptFilter(final Matcher<A> matcher) {
            this.matcher = matcher;
        }

        @Override
        public boolean accept(final A analysedObject) {
            return matcher.matches(analysedObject);
        }
    }

    private static final class ParametersCreator<A> implements Creator<A, Object[]> {

        private final int numberOfParameters;

        private ParametersCreator(final int numberOfParameters) {
            this.numberOfParameters = numberOfParameters;
        }

        @Override
        public Object[] create(final A analysed) {
            Object[] params = new Object[numberOfParameters];
            int ptr = BREADCRUMBS.size();
            if (numberOfParameters > ptr + 1) {
                throw new IllegalArgumentException("Parameterized test has " + numberOfParameters +
                                                           " parameters, where only " + ptr + " available");
            }
            params[0] = analysed;
            for (int i = 1; i < numberOfParameters; i++) {
                params[i] = BREADCRUMBS.get(--ptr);
            }
            return params;
        }
    }

}

/*
 * Copyright 2016-2018 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.gchq.gaffer.doc.function;

import uk.gov.gchq.koryphe.impl.function.Multiply;
import uk.gov.gchq.koryphe.tuple.n.Tuple2;

public class MultiplyExample extends FunctionExample {
    public static void main(final String[] args) {
        new MultiplyExample().runAndPrint();
    }

    public MultiplyExample() {
        super(Multiply.class, "The input integers are multiplied together.");
    }

    @Override
    public void runExamples() {
        objectMultiply();
    }

    public void objectMultiply() {
        // ---------------------------------------------------------
        final Multiply function = new Multiply();
        // ---------------------------------------------------------

        runExample(function,
                null,
                new Tuple2<>(2, 3),
                new Tuple2<>(null, 3),
                new Tuple2<>(2, null),
                new Tuple2<>(2.1, 3.1)
        );
    }
}

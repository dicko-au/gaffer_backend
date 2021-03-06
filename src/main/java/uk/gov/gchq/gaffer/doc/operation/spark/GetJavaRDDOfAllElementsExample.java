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
package uk.gov.gchq.gaffer.doc.operation.spark;

import uk.gov.gchq.gaffer.data.elementdefinition.view.View;
import uk.gov.gchq.gaffer.operation.OperationException;
import uk.gov.gchq.gaffer.spark.operation.javardd.GetJavaRDDOfAllElements;

/**
 * An example showing how the {@link GetJavaRDDOfAllElements} operation is used from Java.
 */
public class GetJavaRDDOfAllElementsExample extends SparkOperationExample {
    public static void main(final String[] args) throws OperationException {
        new GetJavaRDDOfAllElementsExample().runAndPrint();
    }

    public GetJavaRDDOfAllElementsExample() {
        super(GetJavaRDDOfAllElements.class, getDescription());
    }

    private static String getDescription() {
        return "All the elements in a graph can be returned "
                + "as a JavaRDD by using the operation GetJavaRDDOfAllElements. "
                + "Some examples follow. \n"
                + RFILE_READER_DESCRIPTION;
    }

    @Override
    protected void _runExamples() {
        getJavaRddOfAllElements();
        getJavaRddOfAllElementsReturningEdgesOnly();
    }

    public void getJavaRddOfAllElements() {
        // ---------------------------------------------------------
        final GetJavaRDDOfAllElements operation = new GetJavaRDDOfAllElements();
        // ---------------------------------------------------------

        runExample(operation, null);
    }

    public void getJavaRddOfAllElementsReturningEdgesOnly() {
        // ---------------------------------------------------------
        final GetJavaRDDOfAllElements operation = new GetJavaRDDOfAllElements.Builder()
                .view(new View.Builder()
                        .edge("edge")
                        .build())
                .build();
        // ---------------------------------------------------------

        runExample(operation, null);
    }
}

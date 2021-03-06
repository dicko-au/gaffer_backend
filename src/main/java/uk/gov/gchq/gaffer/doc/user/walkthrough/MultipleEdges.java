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

package uk.gov.gchq.gaffer.doc.user.walkthrough;

import com.google.common.collect.Iterables;
import org.apache.commons.io.IOUtils;

import uk.gov.gchq.gaffer.commonutil.StreamUtil;
import uk.gov.gchq.gaffer.commonutil.iterable.CloseableIterable;
import uk.gov.gchq.gaffer.data.element.Element;
import uk.gov.gchq.gaffer.data.elementdefinition.view.View;
import uk.gov.gchq.gaffer.doc.user.generator.RoadAndRoadUseElementGenerator;
import uk.gov.gchq.gaffer.graph.Graph;
import uk.gov.gchq.gaffer.operation.OperationException;
import uk.gov.gchq.gaffer.operation.data.EntitySeed;
import uk.gov.gchq.gaffer.operation.impl.add.AddElements;
import uk.gov.gchq.gaffer.operation.impl.get.GetElements;
import uk.gov.gchq.gaffer.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultipleEdges extends UserWalkthrough {
    public MultipleEdges() {
        super("Multiple Edges", "RoadAndRoadUse", RoadAndRoadUseElementGenerator.class);
    }

    @Override
    public CloseableIterable<? extends Element> run() throws OperationException, IOException {
        // [generate] Create some edges from the simple data file using our Road Use generator class
        // ---------------------------------------------------------
        final List<Element> elements = new ArrayList<>();
        final RoadAndRoadUseElementGenerator dataGenerator = new RoadAndRoadUseElementGenerator();
        for (final String line : IOUtils.readLines(StreamUtil.openStream(getClass(), dataPath))) {
            Iterables.addAll(elements, dataGenerator._apply(line));
        }
        // ---------------------------------------------------------
        print("Elements generated from the data file.");
        for (final Element element : elements) {
            print("GENERATED_EDGES", element.toString());
        }
        print("");


        // [graph] Create a graph using our schema and store properties
        // ---------------------------------------------------------
        final Graph graph = new Graph.Builder()
                .config(getDefaultGraphConfig())
                .addSchemas(StreamUtil.openStreams(getClass(), schemaPath))
                .storeProperties(getDefaultStoreProperties())
                .build();
        // ---------------------------------------------------------


        // [user] Create a user
        // ---------------------------------------------------------
        final User user = new User("user01");
        // ---------------------------------------------------------


        // [add] Add the edges to the graph
        // ---------------------------------------------------------
        final AddElements addElements = new AddElements.Builder()
                .input(elements)
                .build();
        graph.execute(addElements, user);
        // ---------------------------------------------------------
        print("The elements have been added.");


        // [get simple] Get all the edges related to vertex 10
        // ---------------------------------------------------------
        final GetElements getElements = new GetElements.Builder()
                .input(new EntitySeed("10"))
                .build();
        final CloseableIterable<? extends Element> results = graph.execute(getElements, user);
        // ---------------------------------------------------------
        print("\nAll elements containing vertex 10");
        print("\nNotice that the edges are aggregated within their groups");
        for (final Element e : results) {
            print("GET_ELEMENTS_RESULT", e.toString());
        }


        // [get] Rerun the previous query with a View to specify which subset of results we want
        // ---------------------------------------------------------
        final View view = new View.Builder()
                .edge("RoadHasJunction")
                .build();
        final GetElements getRoadHasJunctionEdges = new GetElements.Builder()
                .input(new EntitySeed("10"))
                .view(view)
                .build();
        final CloseableIterable<? extends Element> filteredResults = graph.execute(getRoadHasJunctionEdges, user);
        // ---------------------------------------------------------
        print("\nAll RoadHasJunction edges containing vertex 10\n");
        for (final Element e : filteredResults) {
            print("GET_ROAD_HAS_JUNCTION_EDGES_RESULT", e.toString());
        }

        return filteredResults;
    }

    public static void main(final String[] args) throws OperationException, IOException {
        final MultipleEdges walkthrough = new MultipleEdges();
        walkthrough.run();
    }
}

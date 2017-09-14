# Accumulo Operation Examples
_This page has been generated from code. To make any changes please update the example doc in the [doc](https://github.com/gchq/Gaffer/tree/master/doc/src/main/java/uk/gov/gchq/gaffer/doc) module, run it and replace the content of this page with the output._


1. [GetElementsBetweenSets](#getelementsbetweensets-example)
2. [GetElementsInRanges](#getelementsinranges-example)
3. [GetElementsWithinSet](#getelementswithinset-example)


GetElementsBetweenSets example
-----------------------------------------------
See javadoc - [uk.gov.gchq.gaffer.accumulostore.operation.impl.GetElementsBetweenSets](http://gchq.github.io/Gaffer/uk/gov/gchq/gaffer/accumulostore/operation/impl/GetElementsBetweenSets.html).

### Required fields
No required fields


#### Get elements within set of vertices 1 and 2 and 4

Using this simple directed graph:

```

    --> 4 <--
  /     ^     \
 /      |      \
1  -->  2  -->  3
         \
           -->  5
```

As Java:


```java
final GetElementsBetweenSets operation = new GetElementsBetweenSets.Builder()
        .input(new EntitySeed(1))
        .inputB(new EntitySeed(2), new EntitySeed(4))
        .build();
```

As JSON:


```json
{
  "class" : "uk.gov.gchq.gaffer.accumulostore.operation.impl.GetElementsBetweenSets",
  "input" : [ {
    "vertex" : 1,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  } ],
  "inputB" : [ {
    "vertex" : 2,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  }, {
    "vertex" : 4,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  } ]
}
```

Result:

```
Entity[vertex=1,group=entity,properties=Properties[count=<java.lang.Integer>3]]
Edge[source=1,destination=2,directed=true,group=edge,properties=Properties[count=<java.lang.Integer>3]]
Edge[source=1,destination=4,directed=true,group=edge,properties=Properties[count=<java.lang.Integer>1]]
```
-----------------------------------------------

#### Get elements within set of vertices 1 and 2 and 4 with count greater than 2

Using this simple directed graph:

```

    --> 4 <--
  /     ^     \
 /      |      \
1  -->  2  -->  3
         \
           -->  5
```

As Java:


```java
final GetElementsBetweenSets operation = new GetElementsBetweenSets.Builder()
        .input(new EntitySeed(1))
        .inputB(new EntitySeed(2), new EntitySeed(4))
        .view(new View.Builder()
                .entity("entity", new ViewElementDefinition.Builder()
                        .preAggregationFilter(new ElementFilter.Builder()
                                .select("count")
                                .execute(new IsMoreThan(2))
                                .build())
                        .build())
                .edge("edge", new ViewElementDefinition.Builder()
                        .preAggregationFilter(new ElementFilter.Builder()
                                .select("count")
                                .execute(new IsMoreThan(2))
                                .build())
                        .build())
                .build())
        .build();
```

As JSON:


```json
{
  "class" : "uk.gov.gchq.gaffer.accumulostore.operation.impl.GetElementsBetweenSets",
  "view" : {
    "edges" : {
      "edge" : {
        "preAggregationFilterFunctions" : [ {
          "predicate" : {
            "class" : "uk.gov.gchq.koryphe.impl.predicate.IsMoreThan",
            "orEqualTo" : false,
            "value" : 2
          },
          "selection" : [ "count" ]
        } ]
      }
    },
    "entities" : {
      "entity" : {
        "preAggregationFilterFunctions" : [ {
          "predicate" : {
            "class" : "uk.gov.gchq.koryphe.impl.predicate.IsMoreThan",
            "orEqualTo" : false,
            "value" : 2
          },
          "selection" : [ "count" ]
        } ]
      }
    }
  },
  "input" : [ {
    "vertex" : 1,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  } ],
  "inputB" : [ {
    "vertex" : 2,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  }, {
    "vertex" : 4,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  } ]
}
```

Result:

```
Entity[vertex=1,group=entity,properties=Properties[count=<java.lang.Integer>3]]
Edge[source=1,destination=2,directed=true,group=edge,properties=Properties[count=<java.lang.Integer>3]]
```
-----------------------------------------------




GetElementsInRanges example
-----------------------------------------------
See javadoc - [uk.gov.gchq.gaffer.accumulostore.operation.impl.GetElementsInRanges](http://gchq.github.io/Gaffer/uk/gov/gchq/gaffer/accumulostore/operation/impl/GetElementsInRanges.html).

### Required fields
No required fields


#### Get all elements in the range from entity 1 to entity 4

Using this simple directed graph:

```

    --> 4 <--
  /     ^     \
 /      |      \
1  -->  2  -->  3
         \
           -->  5
```

As Java:


```java
final GetElementsInRanges operation = new GetElementsInRanges.Builder()
        .input(new Pair<>(new EntitySeed(1), new EntitySeed(4)))
        .build();
```

As JSON:


```json
{
  "class" : "uk.gov.gchq.gaffer.accumulostore.operation.impl.GetElementsInRanges",
  "input" : [ {
    "class" : "uk.gov.gchq.gaffer.commonutil.pair.Pair",
    "first" : {
      "uk.gov.gchq.gaffer.operation.data.EntitySeed" : {
        "vertex" : 1,
        "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
      }
    },
    "second" : {
      "uk.gov.gchq.gaffer.operation.data.EntitySeed" : {
        "vertex" : 4,
        "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
      }
    }
  } ]
}
```

Result:

```
Entity[vertex=1,group=entity,properties=Properties[count=<java.lang.Integer>3]]
Edge[source=1,destination=2,directed=true,matchedVertex=SOURCE,group=edge,properties=Properties[count=<java.lang.Integer>3]]
Edge[source=1,destination=4,directed=true,matchedVertex=SOURCE,group=edge,properties=Properties[count=<java.lang.Integer>1]]
Entity[vertex=2,group=entity,properties=Properties[count=<java.lang.Integer>1]]
Edge[source=2,destination=3,directed=true,matchedVertex=SOURCE,group=edge,properties=Properties[count=<java.lang.Integer>2]]
Edge[source=2,destination=4,directed=true,matchedVertex=SOURCE,group=edge,properties=Properties[count=<java.lang.Integer>1]]
Edge[source=2,destination=5,directed=true,matchedVertex=SOURCE,group=edge,properties=Properties[count=<java.lang.Integer>1]]
Edge[source=1,destination=2,directed=true,matchedVertex=DESTINATION,group=edge,properties=Properties[count=<java.lang.Integer>3]]
Entity[vertex=3,group=entity,properties=Properties[count=<java.lang.Integer>2]]
Edge[source=3,destination=4,directed=true,matchedVertex=SOURCE,group=edge,properties=Properties[count=<java.lang.Integer>4]]
Edge[source=2,destination=3,directed=true,matchedVertex=DESTINATION,group=edge,properties=Properties[count=<java.lang.Integer>2]]
Entity[vertex=4,group=entity,properties=Properties[count=<java.lang.Integer>1]]
Edge[source=1,destination=4,directed=true,matchedVertex=DESTINATION,group=edge,properties=Properties[count=<java.lang.Integer>1]]
Edge[source=2,destination=4,directed=true,matchedVertex=DESTINATION,group=edge,properties=Properties[count=<java.lang.Integer>1]]
Edge[source=3,destination=4,directed=true,matchedVertex=DESTINATION,group=edge,properties=Properties[count=<java.lang.Integer>4]]
```
-----------------------------------------------

#### Get all elements in the range from entity 4 to edge 4_5

Using this simple directed graph:

```

    --> 4 <--
  /     ^     \
 /      |      \
1  -->  2  -->  3
         \
           -->  5
```

As Java:


```java
final GetElementsInRanges operation = new GetElementsInRanges.Builder()
        .input(new Pair<>(new EntitySeed(4), new EdgeSeed(4, 5, DirectedType.EITHER)))
        .build();
```

As JSON:


```json
{
  "class" : "uk.gov.gchq.gaffer.accumulostore.operation.impl.GetElementsInRanges",
  "input" : [ {
    "class" : "uk.gov.gchq.gaffer.commonutil.pair.Pair",
    "first" : {
      "uk.gov.gchq.gaffer.operation.data.EntitySeed" : {
        "vertex" : 4,
        "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
      }
    },
    "second" : {
      "uk.gov.gchq.gaffer.operation.data.EdgeSeed" : {
        "source" : 4,
        "destination" : 5,
        "directedType" : "EITHER",
        "matchedVertex" : "SOURCE",
        "class" : "uk.gov.gchq.gaffer.operation.data.EdgeSeed"
      }
    }
  } ]
}
```

Result:

```
Entity[vertex=4,group=entity,properties=Properties[count=<java.lang.Integer>1]]
Edge[source=1,destination=4,directed=true,matchedVertex=DESTINATION,group=edge,properties=Properties[count=<java.lang.Integer>1]]
Edge[source=2,destination=4,directed=true,matchedVertex=DESTINATION,group=edge,properties=Properties[count=<java.lang.Integer>1]]
Edge[source=3,destination=4,directed=true,matchedVertex=DESTINATION,group=edge,properties=Properties[count=<java.lang.Integer>4]]
Entity[vertex=5,group=entity,properties=Properties[count=<java.lang.Integer>3]]
```
-----------------------------------------------




GetElementsWithinSet example
-----------------------------------------------
See javadoc - [uk.gov.gchq.gaffer.accumulostore.operation.impl.GetElementsWithinSet](http://gchq.github.io/Gaffer/uk/gov/gchq/gaffer/accumulostore/operation/impl/GetElementsWithinSet.html).

### Required fields
No required fields


#### Get elements within set of vertices 1 and 2 and 3

Using this simple directed graph:

```

    --> 4 <--
  /     ^     \
 /      |      \
1  -->  2  -->  3
         \
           -->  5
```

As Java:


```java
final GetElementsWithinSet operation = new GetElementsWithinSet.Builder()
        .input(new EntitySeed(1), new EntitySeed(2), new EntitySeed(3))
        .build();
```

As JSON:


```json
{
  "class" : "uk.gov.gchq.gaffer.accumulostore.operation.impl.GetElementsWithinSet",
  "input" : [ {
    "vertex" : 1,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  }, {
    "vertex" : 2,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  }, {
    "vertex" : 3,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  } ]
}
```

Result:

```
Entity[vertex=1,group=entity,properties=Properties[count=<java.lang.Integer>3]]
Edge[source=1,destination=2,directed=true,group=edge,properties=Properties[count=<java.lang.Integer>3]]
Entity[vertex=2,group=entity,properties=Properties[count=<java.lang.Integer>1]]
Edge[source=2,destination=3,directed=true,group=edge,properties=Properties[count=<java.lang.Integer>2]]
Entity[vertex=3,group=entity,properties=Properties[count=<java.lang.Integer>2]]
```
-----------------------------------------------

#### Get elements within set of vertices 1 and 2 and 3 with count greater than 2

Using this simple directed graph:

```

    --> 4 <--
  /     ^     \
 /      |      \
1  -->  2  -->  3
         \
           -->  5
```

As Java:


```java
final GetElementsWithinSet operation = new GetElementsWithinSet.Builder()
        .input(new EntitySeed(1), new EntitySeed(2), new EntitySeed(3))
        .view(new View.Builder()
                .entity("entity", new ViewElementDefinition.Builder()
                        .preAggregationFilter(new ElementFilter.Builder()
                                .select("count")
                                .execute(new IsMoreThan(2))
                                .build())
                        .build())
                .edge("edge", new ViewElementDefinition.Builder()
                        .preAggregationFilter(new ElementFilter.Builder()
                                .select("count")
                                .execute(new IsMoreThan(2))
                                .build())
                        .build())
                .build())
        .build();
```

As JSON:


```json
{
  "class" : "uk.gov.gchq.gaffer.accumulostore.operation.impl.GetElementsWithinSet",
  "view" : {
    "edges" : {
      "edge" : {
        "preAggregationFilterFunctions" : [ {
          "predicate" : {
            "class" : "uk.gov.gchq.koryphe.impl.predicate.IsMoreThan",
            "orEqualTo" : false,
            "value" : 2
          },
          "selection" : [ "count" ]
        } ]
      }
    },
    "entities" : {
      "entity" : {
        "preAggregationFilterFunctions" : [ {
          "predicate" : {
            "class" : "uk.gov.gchq.koryphe.impl.predicate.IsMoreThan",
            "orEqualTo" : false,
            "value" : 2
          },
          "selection" : [ "count" ]
        } ]
      }
    }
  },
  "input" : [ {
    "vertex" : 1,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  }, {
    "vertex" : 2,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  }, {
    "vertex" : 3,
    "class" : "uk.gov.gchq.gaffer.operation.data.EntitySeed"
  } ]
}
```

Result:

```
Entity[vertex=1,group=entity,properties=Properties[count=<java.lang.Integer>3]]
Edge[source=1,destination=2,directed=true,group=edge,properties=Properties[count=<java.lang.Integer>3]]
```
-----------------------------------------------




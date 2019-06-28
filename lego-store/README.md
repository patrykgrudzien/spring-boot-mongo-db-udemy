#### Full text search
All we have to do is - use the `@TextIndexed` annotation.

* It can be used on root level properties.
* It can be added on sub-documents and array elements.

90% of the work is taken care of by `Spring Data MongoDB`. 

(I need to specify what properties need to be included while doing a
full text search).
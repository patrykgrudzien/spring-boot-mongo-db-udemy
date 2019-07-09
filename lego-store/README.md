#### Section 7 - Full text search
All we have to do is - use the `@TextIndexed` annotation.

* It can be used on root level properties.
* It can be added on sub-documents and array elements.

90% of the work is taken care of by `Spring Data MongoDB`. 

(I need to specify what properties need to be included while doing a
full text search).

#### Section 8 - Data Migrations (changing the data structure)
1. Adding new collection is very easy (it's inserted automatically by `MongoRepository`)
    1. Simply put `@Document` annotation on a new collection class e.g. `Customer.java`
2. Adding new field to the existing collection
    1. Delete annotation `@Transient` from `LegoSet#nbParts`
3. Removing fields
4. Trying to modify the field name
    1. `LegoSet#deliveryInfo` (it's also possible when creating new document and `MongoRepository` handles that)
    2. Problem starts appearing when we want to `getAll()` documents:
        1. All existing documents that had previous `name` of that field, will have `null` value for it as 
        they were set previously with `old` name
        
**Changing a property name is the `MOST DIFFICULT` scenario during updating the data structure!**

To make it work everywhere, we need to create mongo script and update all possible places.
Or use `MongoBee` which is the `data migrations` library.

#### Section 9 - Document References
* Relationships between documents in MongoDB are called `References`.
* They allow us to link documents that are spread across multiple collections.
* To avoid making too many references we can create `Embedded Documents`.
* We can also use `Referenced Documents`.
* **We don't have cascading by default !**

**THE NATURE OF `NoSQL` IS TO MINIMIZE RELATIONSHIPS BETWEEN COLLECTIONS!**
**Use `@DBRef` only when really needed.**
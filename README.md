# Merger
<p>
    Merger is library to aid with recursively merging data from one object into another. 
</p>

### Motivation
<p>
    Partial HTTP PUT operations.
</p>

### The Merge Context
<p>
    The merge context holds information about your @Mergeable annotated beans. Each time merger is
    asked to merge any two objects the context is used to determine key details such as
    field type, methods, merge handlers..
</p>
<p>
    The merge context is built once and lives for the life of your application.
</p>

### Usage
<p>
    Any bean annotated with @Mergeable will have it's fields mapped into the merge context.
</p>

```java
@Mergeable
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Address address;

    // Getters and Setters
}

```
```java
Customer source = new Customer();
Customer destination new Customer();

source.setFirstName("Foo");
source.setLastName("Boo");

Merger merger = new Merger(new ClasspathScanningMergeContext(Customer.class));
merger.merge(source).into(destination);

```

### Merge Handlers
<p>
   By default, merger will use BasicMergeHandler. This handler takes the source bean's value and calls the
   required set method on the destination bean. You can however change this merge handler by specifying the @MergeHandler
   above any field.
</p>
<p>
    Custom merge handlers can be created by implementing the com.mymo.merger.handler.MergeHandler interface.
</p>

```java
@Mergeable
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    
    @MergeHandler(MyCustomAddressMergeHandler.class)
    private Address address;

    // Getters and Setters
}
```

### Ignoring fields / beans
<p>
    You can configure merger to "ignore" (don't merge values) on any class or specific field by 
    specifying the @Ignore annotation.
</p>

```java
@Mergeable
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    
    @Ignore
    private Address address;

    // Getters and Setters
}
```


### Benchmarks


Dozer
```
Merge Iterations:1.                 Time: 0.152 seconds
Merge Iterations:10.                Time: 0.058 seconds
Merge Iterations:100.               Time: 0.353 seconds
Merge Iterations:1,000.             Time: 0.951 seconds
Merge Iterations:10,000.            Time: 2.059 seconds
Merge Iterations:100,000.           Time: 10.725 seconds
Merge Iterations:1,000,000.         Time: 104.784 seconds
```

Merger
```
Merge Iterations: 1.                 Time: 0.002 seconds
Merge Iterations: 10.                Time: 0.022 seconds
Merge Iterations: 100.               Time: 0.024 seconds
Merge Iterations: 1,000.             Time: 0.103 seconds
Merge Iterations: 10,000.            Time: 0.186 seconds
Merge Iterations: 100,000.           Time: 0.45 seconds
Merge Iterations: 1,000,000.         Time: 3.439 seconds
```

java
```
Merge Operations: 1.                 Time: 0.0 seconds
Merge Operations: 10.                Time: 0.0 seconds
Merge Operations: 100.               Time: 0.0 seconds
Merge Operations: 1,000.             Time: 0.008 seconds
Merge Operations: 10,000.            Time: 0.057 seconds
Merge Operations: 100,000.           Time: 0.02 seconds
Merge Operations: 1,000,000.         Time: 0.023 seconds
```
http://dozer.sourceforge.net/

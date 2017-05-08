package com.mymo.merger;

import com.mymo.merger.exception.*;
import com.mymo.merger.fixtures.*;
import org.dozer.*;
import org.junit.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

@Ignore
public class BenchmarkTest {
    private static final int ITERATIONS = 1000000;
    private Order sourceOrder;
    private Order destinationOrder;
    private Merger merger;

    @Before
    public void setUp() {
        sourceOrder = FixtureFactory.getOrder();
        destinationOrder = FixtureFactory.getOrder();

        destinationOrder.setCustomer(null);
        destinationOrder.getProduct().setPrice(null);

        merger = new Merger(new ClasspathScanningMergeContext(TestConfiguration.class));
    }

    @Test
    public void test() throws MergeException {
        System.out.println("Iterations,Time (seconds)");

        for (int i = 1; i <= ITERATIONS; i *= 10) {
            long startTime = System.currentTimeMillis();
            for (int itr = 0; itr <= i; itr++) {
                merger.merge(sourceOrder).into(destinationOrder);
            }
            System.out.println(String.format("Merge Iterations: %,d. Time: %s seconds", i, (System.currentTimeMillis() - startTime)/ 1000D));
        }
    }

    @Test
    public void test2() {
        Mapper mapper = new DozerBeanMapper();
        System.out.println("Iterations,Time (seconds)");

        for (int i = 1; i <= ITERATIONS; i *= 10) {
            long startTime = System.currentTimeMillis();
            for (int itr = 0; itr <= i; itr++) {
                mapper.map(sourceOrder, Order.class);
            }
            System.out.println(String.format("Merge Iterations:%,d. Time: %s seconds", i, (System.currentTimeMillis() - startTime)/ 1000D));
        }
    }

    @Test
    public void test3() {
        System.out.println("Iterations,Time (seconds)");

        for (int i = 1; i <= ITERATIONS; i *= 10) {
            long startTime = System.currentTimeMillis();
            for (int itr = 0; itr <= i; itr++) {

                if (sourceOrder.getId() != null) {
                    destinationOrder.setId(sourceOrder.getId());
                }
                if (sourceOrder.getQuantity() != null) {
                    destinationOrder.setQuantity(sourceOrder.getQuantity());
                }
                if (sourceOrder.getOrderDate() != null) {
                    destinationOrder.setOrderDate(sourceOrder.getOrderDate());
                }
                if (sourceOrder.getProduct() != null) {
                    destinationOrder.setProduct(mergeProduct(sourceOrder.getProduct(), destinationOrder.getProduct()));
                }
                if (sourceOrder.getCustomer() != null) {
                    destinationOrder.setCustomer(mergeCustomer(sourceOrder.getCustomer(), destinationOrder.getCustomer()));
                }
            }
            System.out.println(String.format("Merge Operations: %,d. Time: %s seconds", i, (System.currentTimeMillis() - startTime)/ 1000D));
        }
    }


    private Product mergeProduct(Product source, Product destination) {
        if (destination == null) {
            return source;
        }

        if(source.getId() != null) {
            destination.setId(source.getId());
        }
        if (source.getBarCode() != null) {
            destination.setBarCode(sourceOrder.getProduct().getBarCode());
        }
        if (source.getDescription() != null) {
            destination.setDescription(source.getDescription());
        }
        if (source.getName() != null) {
            destination.setName(source.getName());
        }
        if (source.getPrice() != null) {
            destination.setPrice(source.getPrice());
        }
        if(source.getManufacturer() != null) {
            destination.setManufacturer(mergeAddress(source.getManufacturer(), destination.getManufacturer()));
        }

        return destination;
    }

    private Customer mergeCustomer(Customer source, Customer destination) {
        if (destination == null) {
            return source;
        }

        if (source.getFirstName() != null) {
            destination.setFirstName(source.getFirstName());
        }
        if (source.getLastName() != null) {
            destination.setLastName(source.getLastName());
        }
        if (source.getEmailAddress() != null) {
            destination.setLastName(source.getLastName());
        }
        if (source.getId() != null) {
            destination.setId(source.getId());
        }
        if (source.getAddress() != null) {
            destination.setAddress(source.getAddress());
        }
        return destination;
    }

    private Address mergeAddress(Address source, Address destination) {
        if (destination == null) {
            return  source;
        }
        if (source.getId() != null) {
            destination.setId(source.getId());
        }
        if (source.getAptOrSuite() != null) {
            destination.setAptOrSuite(source.getAptOrSuite());
        }
        if (source.getCity() != null) {
            destination.setCity(source.getCity());
        }
        if (source.getCompanyName() != null) {
            destination.setCompanyName(source.getCompanyName());
        }
        if (source.getCountry() != null) {
            destination.setCountry(source.getCountry());
        }
        if (source.getLine1() != null) {
            destination.setLine1(source.getLine1());
        }
        if (source.getLine2() != null) {
            destination.setLine2(source.getLine2());
        }
        if (source.getPhone1() != null) {
            destination.setPhone1(source.getPhone1());
        }
        if (source.getPhone2() != null) {
            destination.setPhone2(source.getPhone2());
        }
        if (source.getPostalCode() != null) {
            destination.setPostalCode(source.getPostalCode());
        }

        return destination;
    }

}

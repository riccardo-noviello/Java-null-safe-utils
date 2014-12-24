package com.riccardonoviello.nullutils;

import com.riccardonoviello.nullutils.models.Address;
import com.riccardonoviello.nullutils.models.Company;
import com.riccardonoviello.nullutils.models.Director;
import com.riccardonoviello.nullutils.models.Person;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author laptop
 */
public class NullSafeUtilsTest {

    @Test
    public void isAccessible_canAccess() {
        Person person = new Person();
        person.setName("Riccardo");

        Address address1 = new Address();
        address1.setStreet("56 Christchurch road");
        address1.setCountry("newport, UK");
        person.setAddress(address1);

        assertFalse(NullSafeUtils.isAccessible(person, "address", "postcode"));

        address1.setPostcode("np197sp");
        person.setAddress(address1);

        assertTrue(NullSafeUtils.isAccessible(person, "address", "postcode"));
    }

    @Test
    public void get_doesNotThrowNullPointer_nullValue() throws NullPointerException {
        Person person = new Person();
        person.setName("Riccardo");

        Address address1 = new Address();
        address1.setStreet("56 Christchurch road");
        address1.setCountry("newport, UK");
        person.setAddress(address1);

        assertNull(NullSafeUtils.get(person, "address", "postcode"));

    }

    @Test
    public void get_doesNotThrowNullPointer_notNull() throws NullPointerException {
        Person person = new Person();
        person.setName("Riccardo");

        Address address1 = new Address();
        address1.setStreet("56 Christchurch road");
        address1.setCountry("newport, UK");
        address1.setPostcode("np197sp");
        person.setAddress(address1);

        assertNotNull(NullSafeUtils.get(person, "address", "postcode"));
    }

    @Test
    public void get_doesNotThrowNullPointer_deepRelationNotNull() throws NullPointerException {
        Company company = buildCompany();
        
        // find where the director of the company lives
        assertNotNull(NullSafeUtils.get(company, "director", "address", "street"));

        // make sure we have the correct address
        assertEquals("33 fake road", NullSafeUtils.get(company, "director", "address", "street"));

        
    }
    
    
    @Test
    public void getOr_doesNotThrowNullPointer_notNull() throws NullPointerException {
        Company company = buildCompany();
        
        assertNotNull(NullSafeUtils.getOr(company, "director", "address", "street"));

        assertEquals("33 fake road", NullSafeUtils.get(company, "director", "address", "street"));

        
    }
    
    
    @Test
    public void getOr_doesNotThrowNullPointer_alternative() throws NullPointerException {
        Company company = buildCompany();
        
        String alternative = "not found";
        
        // the postoce is null so we are expecting to be returned the alternative
        assertEquals(alternative, NullSafeUtils.getOr(company, "director", "address", "postcode").or(alternative));        
    }
    
    @Test
    public void demonstration(){
        
        Company company = buildCompany();
        String addressFromJava = null;
        String addressFromUtils = null;
        
        // in plain Java this would typically be
        if (company != null) {
            if (company.getDirector() != null) {
                if (company.getDirector().getAddress() != null) {
                  // do something with the Street
                    addressFromJava= company.getDirector().getAddress().getStreet();
                }
            }
        }
        
        // This is the same code using Nullutils
        addressFromUtils = (String) NullSafeUtils.get(company, "director", "address", "street");
        
        assertEquals(addressFromJava, addressFromUtils);
        
    }

    /**
     * Builds a Company with a Director and a Director's Address
     * @return 
     */
    private Company buildCompany() {
        Company company = new Company();
        company.setName("My Fake Company LTD");

        Director director = new Director();
        director.setName("Mr Grumpy");

        Address address = new Address();
        address.setStreet("33 fake road");
        address.setCountry("Newport, UK");
        address.setPostcode(null);

        director.setAddress(address);
        company.setDirector(director);
        return company;
    }
}

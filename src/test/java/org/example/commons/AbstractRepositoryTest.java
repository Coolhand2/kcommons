package org.example.commons;

import org.example.commons.entities.Organization;
import org.example.commons.repositories.api.OrganizationRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class AbstractRepositoryTest {

    @Deployment public static Archive<?> deploy() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.example")
                .addAsResource("META-INF/test-persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private OrganizationRepository organizations;

    @Test
    public void testFindAll() {
        Organization o1 = Organization.builder().name("Name 1").build();
        Organization o2 = Organization.builder().name("Name 2").build();
        Organization o3 = Organization.builder().name("Name 3").build();

        organizations.create(o1, o2);

        List<Organization> list = organizations.findAll();
        assertTrue(list.containsAll(Arrays.asList(o1, o2)));
        assertFalse(list.contains(o3));

        organizations.create(o3);

        list = organizations.findAll();
        assertTrue(list.contains(o3));
    }

    @Test
    public void testFindById() {
        Organization o1 = Organization.builder().name("Name 1").build();
        Organization o2 = Organization.builder().name("Name 2").build();

        organizations.create(o1, o2);

        Organization o3 = organizations.findById(o1.getId());
        assertEquals(o1, o3);
    }

    @Test
    public void testFindByIds() {
        Organization o1 = Organization.builder().name("Name 1").build();
        Organization o2 = Organization.builder().name("Name 2").build();
        Organization o3 = Organization.builder().name("Name 3").build();

        organizations.create(o1, o2, o3);

        List<Organization> list = organizations.findByIds(o1.getId(), o2.getId());

        assertEquals(2, list.size());
        assertTrue(list.contains(o1));
        assertTrue(list.contains(o2));
        assertFalse(list.contains(o3));
    }

    @Test
    public void testFindKeepsReference() {
        Organization o1 = Organization.builder().name("Name 1").build();
        organizations.create(o1);

        Organization o2 = organizations.findById(o1.getId());
        assertEquals(o1, o2);
        o1.setName("Something Completely Different");
        assertEquals(o1, o2);
    }

    /**
     * Fun note about this test. It fails on the first assert if we depend on Lombok to generate the equals and hashcode
     * methods. The EqualsBuilder.reflectionEquals method from the Apache Commons-Lang package is able to either ignore
     * the right info, or go deep enough into the object to ensure this test actually passes.
     */
    @Test
    public void testFindDoesNotKeepReferenceWithClone() {
        Organization o1 = Organization.builder().name("Name 1").build();
        organizations.create(o1);

        Organization o2 = organizations.findById(o1.getId()).clone();
        assertEquals(o1, o2);
        o1.setName("Something Completely Different");
        assertNotEquals(o1, o2);
    }

    @Test
    public void testDelete() {
        Organization o1 = Organization.builder().name("Name 1").build();
        Organization o2 = Organization.builder().name("Name 2").build();
        Organization o3 = Organization.builder().name("Name 3").build();

        organizations.create(o1, o2, o3);
        organizations.delete(o2);

        List<Organization> list = organizations.findAll();
        assertTrue(list.contains(o1));
        assertFalse(list.contains(o2));
        assertTrue(list.contains(o3));
    }

}

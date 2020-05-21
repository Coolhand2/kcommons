package org.example.commons.repositories;

import org.example.commons.entities.*;
import org.example.commons.entities.filters.UserFilter;
import org.example.commons.repositories.api.UserRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class UserRepositoryTest {
    @Inject
    private UserRepository users;

    private PhoneNumber p1 = new PhoneNumber("123", "123", "1234", false);
    private PhoneNumber p2 = new PhoneNumber("567", "567", "5678", false);
    private PhoneNumber p3 = new PhoneNumber("909", "909", "9090", false);
    private Address a1 = new Address("ABC", "ABC", "ABC", "ABC", "ABC", "ABC", false);
    private Address a2 = new Address("DEF", "DEF", "DEF", "DEF", "DEF", "DEF", false);
    private Address a3 = new Address("GHI", "GHI", "GHI", "GHI", "GHI", "GHI", false);
    private User u1 = new User(0L, "ABC", "ABC", "ABC", "ABC", p1, a1, UserType.GUEST, UserStatus.DISABLED, UserRole.NONE, Arrays.asList(), false);
    private User u2 = new User(0L, "DEF", "DEF", "DEF", "DEF", p2, a2, UserType.MEMBER, UserStatus.ACTIVE, UserRole.HELPDESK, Arrays.asList(), false);
    private User u3 = new User(0L, "GHI", "GHI", "GHI", "GHI", p3,  a3,  UserType.MEMBER, UserStatus.UNVERIFIED, UserRole.SME, Arrays.asList(), false);

    @Before
    public void setup() {
        users.create(u1, u2, u3);
    }

    @Test
    public void testFilterById() {
        UserFilter filter = new UserFilter();
        filter.setId(String.valueOf(u2.getId()));
        List<User> userList = users.filter(filter);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u2));
    }

    @Test
    public void testFilterByUsername() {
        UserFilter filter = new UserFilter();
        filter.setUsername("E");
        List<User> userList = users.filter(filter);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u2));
    }

    @Test
    public void testFilterByEmail() {
        UserFilter filter = new UserFilter();
        filter.setEmail("E");
        List<User> userList = users.filter(filter);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u2));
    }

    @Test
    public void testFilterByPhone() {
        UserFilter filter = new UserFilter();
        filter.setPhoneNumber("6");
        List<User> userList = users.filter(filter);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u2));
    }

    @Test
    public void testFilterByAddress() {
        UserFilter filter = new UserFilter();
        filter.setAddress("E");
        List<User> userList = users.filter(filter);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u2));
    }

    @Test
    public void testFilterByType() {
        UserFilter filter = new UserFilter();
        filter.setType(Arrays.asList(UserType.GUEST));
        List<User> userList = users.filter(filter);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u1));
    }

    @Test
    public void testFilterByStatus() {
        UserFilter filter = new UserFilter();
        filter.setStatus(Arrays.asList(UserStatus.ACTIVE));
        List<User> userList = users.filter(filter);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u2));
    }

    @Test
    public void testFilterByRole() {
        UserFilter filter = new UserFilter();
        filter.setRole(Arrays.asList(UserRole.NONE));
        List<User> userList = users.filter(filter);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u1));
    }

    @Test
    public void testFilterWithEmptyFilter() {
        UserFilter filter = new UserFilter();
        List<User> userList = users.filter(filter);
        List<User> findAllList = users.findAll();
        Assert.assertEquals(findAllList, userList);
    }

    @Test
    public void testFilterWithComplicatedFilter() {
        UserFilter filter = new UserFilter();
        filter.setEmail("B");
        filter.setType(Arrays.asList(UserType.GUEST));
        List<User> userList = users.filter(filter);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u1));
        filter.setType(Arrays.asList(UserType.MEMBER));
        userList = users.filter(filter);
        Assert.assertTrue(userList.isEmpty());
        filter.setEmail("H");
        userList = users.filter(filter);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u3));
    }

    @Test
    public void testFindByType() {
        List<User> userList = users.findByType(UserType.GUEST);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u1));
    }

    @Test
    public void testFindByStatus() {
        List<User> userList = users.findByStatus(UserStatus.ACTIVE);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u2));
    }

    @Test
    public void testFindByRole() {
        List<User> userList = users.findByRole(UserRole.NONE);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u1));
    }

    @Test
    public void testFindByTypeAndStatus() {
        List<User> userList = users.findByTypeAndStatus(UserType.MEMBER, UserStatus.ACTIVE);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u2));
    }

    @Test
    public void testFindByTypeAndRole() {
        List<User> userList = users.findByTypeAndRole(UserType.GUEST, UserRole.NONE);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u1));
    }

    @Test
    public void testFindByStatusAndRole() {
        List<User> userList = users.findByStatusAndRole(UserStatus.ACTIVE, UserRole.HELPDESK);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u2));
    }

    @Test
    public void testFindByTypeAndStatusAndRole() {
        List<User> userList = users.findByTypeAndStatusAndRole(UserType.MEMBER, UserStatus.UNVERIFIED, UserRole.SME);
        Assert.assertEquals(1, userList.size());
        Assert.assertTrue(userList.contains(u3));
    }

    @Deployment
    public static Archive deploy() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.example")
                .addAsResource("META-INF/test-persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
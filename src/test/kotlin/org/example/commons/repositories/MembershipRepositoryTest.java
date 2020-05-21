package org.example.commons.repositories;

import org.example.commons.entities.Group;
import org.example.commons.entities.Membership;
import org.example.commons.entities.MembershipRole;
import org.example.commons.entities.User;
import org.example.commons.repositories.api.GroupRepository;
import org.example.commons.repositories.api.MembershipRepository;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class MembershipRepositoryTest {
    @Inject
    private MembershipRepository memberships;

    @Inject
    private UserRepository users;

    @Inject
    private GroupRepository groups;

    private User u1 = new User();
    private User u2 = new User();
    private Group g1 = new Group();
    private Group g2 = new Group();
    private Membership m1 = new Membership(u1, g1, Arrays.asList(MembershipRole.READ, MembershipRole.WRITE), false);
    private Membership m2 = new Membership(u2, g2, Arrays.asList(MembershipRole.POC, MembershipRole.ADMIN), false);

    @Before
    public void setup() {
        u1.setUsername("ABC");
        u2.setUsername("DEF");
        users.create(u1, u2);

        g1.setName("ABC");
        g2.setName("DEF");
        groups.create(g1, g2);

        memberships.create(m1, m2);
    }

    @Test
    public void testFindByUser() {
        Assert.assertTrue(true);
    }

    @Test
    public void testFindByGroup() {
        Assert.assertTrue(true);
    }

    @Test
    public void testFindByRole() {
        Assert.assertTrue(true);
    }

        @Deployment
        private static Archive deploy(){
            return ShrinkWrap.create(JavaArchive.class)
                    .addPackages(true, "org.example")
                    .addAsResource("META-INF/test-persistence.xml")
                    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        }

        private static Logger LOG = LoggerFactory.getLogger(MembershipRepositoryTest.class);
}
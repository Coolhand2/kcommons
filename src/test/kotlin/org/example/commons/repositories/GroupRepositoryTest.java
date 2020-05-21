package org.example.commons.repositories;

import org.example.commons.entities.Group;
import org.example.commons.entities.filters.GroupFilter;
import org.example.commons.repositories.api.GroupRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class GroupRepositoryTest {
    @Inject
    private GroupRepository groups;
    private Group g1 = new Group(0, "ABC", "DEF", false);
    private Group g2 = new Group(0, "GHI", "JKL", false);
    private Group g3 = new Group(0, "MNO", "PQR", false);

    @Before
    public void setup() {
        groups.create(g1, g2, g3);
    }

    @Test
    public void testFilterById() {
        GroupFilter filter = new GroupFilter();
        filter.setId(String.valueOf(g1.getId()));
        List<Group> groupList = groups.filter(filter);
        Assert.assertEquals(1, groupList.size());
        Assert.assertTrue(groupList.contains(g1));
    }

    @Test
    public void testFilterByName() {
        GroupFilter filter = new GroupFilter();
        filter.setName("K");
        List<Group> groupList = groups.filter(filter);
        Assert.assertEquals(1, groupList.size());
        Assert.assertTrue(groupList.contains(g2));
    }

    @Test
    public void testFilterByDescription() {
        GroupFilter filter = new GroupFilter();
        filter.setDescription("N");

        List<Group> groupList = groups.filter(filter);
        Assert.assertEquals(1, groupList.size());
        Assert.assertTrue(groupList.contains(g3));
    }

    @Test
    public void testEmptyFilter() {
        GroupFilter filter = new GroupFilter();
        List<Group> groupList = groups.filter(filter);
        Assert.assertEquals(3, groupList.size());
    }

    @Test
    public void testComplexFilter() {
        GroupFilter filter = new GroupFilter();
        filter.setName("E");
        filter.setDescription("B");
        List<Group> groupList = groups.filter(filter);
        Assert.assertEquals(1, groupList.size());
        Assert.assertTrue(groupList.contains(g1));
        filter.setName("Q");
        groupList = groups.filter(filter);
        Assert.assertTrue(groupList.isEmpty());
        filter.setDescription("N");
        groupList = groups.filter(filter);
        Assert.assertEquals(1, groupList.size());
        Assert.assertTrue(groupList.contains(g3));
    }

    @Test
    public void testFindLikeName() {
        List<Group> groupList = groups.findLikeName("Q");
        Assert.assertEquals(1, groupList.size());
        Assert.assertTrue(groupList.contains(g3));
    }

    @Test
    public void testFindLikeDescription() {
        List<Group> groupList = groups.findLikeDescription("B");
        Assert.assertEquals(1, groupList.size());
        Assert.assertTrue(groupList.contains(g1));
    }

    @Test
    public void testFindByName() {
        List<Group> groupList = groups.findByName("E");
        Assert.assertTrue(groupList.isEmpty());
        groupList = groups.findByName("DEF");
        Assert.assertEquals(1, groupList.size());
        Assert.assertTrue(groupList.contains(g1));
    }

    @Deployment
    public static JavaArchive deploy() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.example")
                .addAsResource("META-INF/test-persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
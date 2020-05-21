package org.example.commons

import org.example.commons.entities.*
import org.example.commons.repositories.api.UserRepository
import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.Archive
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.JavaArchive
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import javax.inject.Inject

@RunWith(Arquillian::class)
class AbstractRepositoryTest {
    @Inject
    private val users: UserRepository? = null
    private val p1 = PhoneNumber("", "", "", false)
    private val a1 = Address("", "", "", "", "", "", false)
    private val u1 = User(0L, "ABC", "", "", "", p1, a1, UserType.GUEST, UserStatus.UNVERIFIED, UserRole.NONE, mutableListOf(), false)
    private val u2 = User(0L, "DEF", "", "", "", p1, a1, UserType.GUEST, UserStatus.UNVERIFIED, UserRole.NONE, mutableListOf(), false)
    private val u3 = User(0L, "GHI", "", "", "", p1, a1, UserType.GUEST, UserStatus.UNVERIFIED, UserRole.NONE, mutableListOf(), false)

    @Before
    fun setup() {
        users!!.create(u1, u2, u3)
    }

    @Test
    fun testFindAll() {
        val list: List<User> = users!!.findAll()
        Assert.assertEquals(3, list.size)
        Assert.assertTrue(list.containsAll(listOf(u1, u2, u3)))
    }

    @Test
    fun testFindById() {
        val u4 = users!!.findById(u1.id)
        Assert.assertEquals(u1, u4)
    }

    @Test
    fun testFindByIds() {
        val list: List<User?> = users!!.findByIds(u1.id, u2.id)
        Assert.assertEquals(2, list.size)
        Assert.assertTrue(list.contains(u1))
        Assert.assertTrue(list.contains(u2))
    }

    @Test
    fun testFindByColumn() {
        val list: List<User?> = users!!.findByColumn(User_.username, "DEF")
        Assert.assertEquals(1, list.size)
        Assert.assertTrue(list.contains(u2))
    }

    @Test
    fun testUpdate() {
        u1.username = "ZYX"
        users!!.update(u1)
        val u4: User = users.findById(u1.id)
        Assert.assertEquals(u1, u4)
        Assert.assertNotEquals(u1, u2)
    }

    @Test
    fun testDelete() {
        users!!.delete(u2)
        val list: List<User?> = users.findAll()
        Assert.assertEquals(2, list.size)
        Assert.assertFalse(list.contains(u2))
    }

    @Test
    fun testFindKeepsReference() {
        val u4 = users!!.findById(u1.id)
        Assert.assertEquals(u1, u4)
        u1.username = "Something Completely Different"
        Assert.assertEquals(u1, u4)
    }

    @Test
    fun testFindDoesNotKeepReferenceWithClone() {
        val u4: User = users!!.findById(u1.id).copy()
        Assert.assertEquals(u1, u4)
        u1.username = "Something Completely Different"
        Assert.assertNotEquals(u1, u4)
    }

    companion object {
        @Deployment
        fun deploy(): Archive<*> {
            return ShrinkWrap.create(JavaArchive::class.java)
                    .addPackages(true, "org.example")
                    .addAsResource("META-INF/test-persistence.xml")
                    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
        }
    }
}
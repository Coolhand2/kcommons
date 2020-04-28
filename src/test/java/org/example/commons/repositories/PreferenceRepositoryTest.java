package org.example.commons.repositories;

import org.example.commons.entities.Preference;
import org.example.commons.repositories.api.PreferenceValueRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class PreferenceRepositoryTest {
    @Deployment
    public static Archive<?> deploy() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.example")
                .addAsResource("META-INF/test-persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private PreferenceValueRepository preferences;

    Preference p1, p2, p3;

    @Before
    public void setup() {

    }
}

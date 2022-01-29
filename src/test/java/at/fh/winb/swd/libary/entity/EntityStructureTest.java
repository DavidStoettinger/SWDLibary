package at.fh.winb.swd.libary.entity;

import at.fh.winb.swd.libary.configuration.IntegrationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
class EntityStructureTest {
    @PersistenceContext
    private EntityManager entityManager;
/*
    @Test
    void verifyThatAllEntitiesExtendBaseEntity() {
        entityManager.getMetamodel().getEntities().forEach(
                entityType -> assertThat(BaseEntity.class.isAssignableFrom(entityType.getJavaType())).isTrue()
        );
    }*/
}

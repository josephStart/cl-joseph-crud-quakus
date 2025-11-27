package cl.joseph.repository;

import cl.joseph.entity.UserInfoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserInfoRepository implements PanacheRepository<UserInfoEntity> {
}

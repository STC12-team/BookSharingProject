package ru.innopolis.stc12.booksharing.model.dao;

import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.booksharing.model.dao.entity.Role;
import ru.innopolis.stc12.booksharing.model.dao.interfaces.RoleDao;

@Repository
public class RoleDaoImpl extends AbstractDaoImp<Role> implements RoleDao {
}

package com.ewmw.addr.models.reposotories;

import com.ewmw.addr.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
}

package com.areumjo.todos.service;


import com.areumjo.todos.model.Role;
import com.areumjo.todos.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleRepository rolerepos;

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        rolerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Role findRoleById(long id) {
        return rolerepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) {
        rolerepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        rolerepos.deleteById(id);
    }

    @Transactional
    @Override
    public Role save(Role role) {
        return rolerepos.save(role);
    }
}

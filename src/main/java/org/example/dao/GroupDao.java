package org.example.dao;

import org.example.entity.Group;

import javax.swing.*;
import java.util.List;

public interface GroupDao {
    void createGroup(Group group);

    List<Group> displayAllGroups();

    void updateGroup(int id, Group group);

    void deleteGroup(int id);
}

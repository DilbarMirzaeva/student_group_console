package org.example.service;

import org.example.dao.GroupDao;
import org.example.entity.Group;

import java.util.List;

public class GroupService {
    GroupDao groupDao;

    public GroupService(GroupDao groupDao) {
        this.groupDao=groupDao;
    }

    public void createGroup(Group group) {
        groupDao.createGroup(group);
        System.out.println("Group created successfully");
    }

        public List<Group> displayGroups(){
            return groupDao.displayAllGroups();
        }

    public void updateGroup(int id,Group group) {
        groupDao.updateGroup(id,group);
    }

    public void deleteGroup(int id) {
        groupDao.deleteGroup(id);
    }

}

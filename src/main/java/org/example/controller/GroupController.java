package org.example.controller;

import org.example.entity.Group;
import org.example.service.GroupService;

import java.util.List;

import static org.example.util.InputUtil.getInput;

public class GroupController {
    GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public void createGroup() {
        Group group=getGroup();
        groupService.createGroup(group);
    }

    public List<Group> displayGroups() {
       return groupService.displayGroups();
    }

    public void updateGroup() {
        int id=getInput("Enter the group id to update: ", Integer.class);
        System.out.println("==New group information==");
        Group group=getGroup();
        groupService.updateGroup(id,group);
    }

    public void deleteGroup() {
        int id=getInput("Enter the group id to delete: ", Integer.class);
        groupService.deleteGroup(id);
    }

    public Group getGroup() {
        String name=getInput("Enter the group name:",String.class);
        String description=getInput("Enter the group description:",String.class);
        return new Group(name,description);
    }
}

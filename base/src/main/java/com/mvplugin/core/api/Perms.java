package com.mvplugin.core.api;

import com.dumptruckman.minecraft.pluginbase.permission.Perm;
import com.dumptruckman.minecraft.pluginbase.permission.PermFactory;

public interface Perms {

    Perm ALL_MULTIVERSE = PermFactory.newPerm("multiverse.*").child(Perm.ALL).build();

    Perm CMD_IMPORT = PermFactory.newPerm("cmd.import").commandPermission().build();
}
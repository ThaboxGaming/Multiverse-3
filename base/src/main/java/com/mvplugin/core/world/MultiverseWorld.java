/******************************************************************************
 * Multiverse 2 Copyright (c) the Multiverse Team 2011.                       *
 * Multiverse 2 is licensed under the BSD License.                            *
 * For more information please check the README.md file included              *
 * with this project.                                                         *
 ******************************************************************************/

package com.mvplugin.core.world;

import com.dumptruckman.minecraft.pluginbase.messages.PluginBaseException;
import com.dumptruckman.minecraft.pluginbase.minecraft.BasePlayer;
import com.dumptruckman.minecraft.pluginbase.minecraft.location.FacingCoordinates;
import com.mvplugin.core.SpawnException;
import com.mvplugin.core.minecraft.Difficulty;
import com.mvplugin.core.minecraft.GameMode;
import com.mvplugin.core.minecraft.PortalType;
import com.mvplugin.core.minecraft.WorldEnvironment;
import com.mvplugin.core.minecraft.WorldType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The API for a Multiverse Handled World.
 */
public interface MultiverseWorld {

    /**
     * Gets the name of this world. The name cannot be changed.
     * <p>
     * Note for plugin developers: Usually {@link #getAlias()}
     * is what you want to use instead of this method.
     *
     * @return The name of the world as a String.
     */
    @NotNull
    String getName();

    /**
     * Gets the UUID for this world.
     *
     * This is what Minecraft uses to keep tracks of worlds.
     *
     * @return The UUID for this world.
     */
    @NotNull
    UUID getWorldUID();

    /**
     * Returns the WorldProperties object that contains the backing for all the values of a Multiverse World.
     *
     * All of these properties are generally gettable and settable via the MultiverseWorld class.  Specific uses for
     * the returned object may be to set properties in a different way and to perform more complex actions related
     * to properties.
     *
     * @return The properties for this world.
     */
    @NotNull
    WorldProperties getProperties();

    /**
     * Gets the type of this world. As of 1.2 this will be:
     * FLAT, NORMAL or VERSION_1_1
     * <p>
     * This is <b>not</b> the generator.
     *
     * @return The Type of this world.
     */
    @NotNull
    WorldType getWorldType();

    /**
     * Gets the environment of this world.
     *
     * This environment matches that of the actual game world.
     *
     * @return The environment of this world.
     */
    @NotNull
    WorldEnvironment getEnvironment();

    /**
     * Sets the environment of a world.
     * <p>
     * Note: This will ONLY take effect once the world is unloaded/reloaded.
     *
     * @param environment The new environment for the world.
     */
    void setEnvironment(@NotNull WorldEnvironment environment);

    /**
     * Gets the difficulty of this world.
     *
     * @return The difficulty of this world.
     */
    @NotNull
    Difficulty getDifficulty();

    /**
     * Sets the difficulty of this world and returns {@code true} on success.
     * Valid string values are either an integer of difficulty(0-3) or
     * the name that resides in the Bukkit enum, ex. PEACEFUL
     *
     * @param difficulty The new difficulty.
     * @return True if success, false if the operation failed... for whatever reason.
     */
    boolean setDifficulty(@NotNull Difficulty difficulty);

    /**
     * Gets the world seed of this world.
     *
     * @return The Long version of the seed.
     */
    long getSeed();

    /**
     * Sets the seed of this world.
     *
     * @param seed A Long that is the seed.
     */
    void setSeed(long seed);

    /**
     * Gets the generator of this world.
     *
     * @return The name of the generator.
     */
    @Nullable
    String getGenerator();

    /**
     * Sets the generator of this world.
     *
     * @param generator The new generator's name.
     */
    void setGenerator(@Nullable String generator);

    /**
     * Gets all the names of all properties that can be SET.
     *
     * @return All property names, with alternating colors.
     */
    @NotNull
    String getAllPropertyNames();

    /**
     * Gets the alias of this world.
     * <p>
     * This alias allows users to have a world named "world" but show up in the list as "FernIsland"
     *
     * @return The alias of the world as a String.
     */
    @NotNull
    String getAlias();

    /**
     * Sets the alias of the world.
     *
     * @param alias A string that is the new alias.
     */
    void setAlias(@Nullable String alias);

    /**
     * Gets whether or not PVP is enabled in this world in some form (fake or not).
     *
     * @return True if players can take damage from other players.
     */
    boolean isPVPEnabled();

    /**
     * Turn pvp on or off. This setting is used to set the world's PVP mode.
     *
     * @param pvpMode True to enable PVP damage, false to disable it.
     */
    void setPVPMode(boolean pvpMode);

    /**
     * Gets whether or not this world will display in mv who and mv list regardless if a user has the
     * access permissions to go to this world.
     *
     * @return True if the world will be hidden, false if not.
     * @see {@link #getPrefixChat()}
     */
    boolean isHidden();

    /**
     * Sets whether or not this world will display in mv who and mv list regardless if a user has the
     * access permissions to go to this world.
     *
     * @param hidden Set
     */
    void setHidden(boolean hidden);

    /**
     * Gets whether or not this world will display it's name/alias as a prefix in chat.
     *
     * If the global configuration setting for prefixChat is false, this setting has no effect.
     *
     * @return True if the world name/alias will be used as a prefix in chat, false if not.
     */
    boolean getPrefixChat();

    /**
     * Sets whether or not this world will display it's name/alias as a prefix in chat.
     *
     * If the global configuration setting for prefixChat is false, this setting has no effect.
     *
     * @param prefixChat True if the world name/alias will be used as a prefix in chat, false if not.
     */
    void setPrefixChat(boolean prefixChat);

    /**
     * Gets whether weather is enabled in this world.
     *
     * @return True if weather events will occur, false if not.
     */
    boolean isWeatherEnabled();

    /**
     * Sets whether or not there will be weather events in a given world.
     * If set to false, Multiverse will disable the weather in the world immediately.
     *
     * @param enableWeather True if weather events should occur in a world, false if not.
     */
    void setEnableWeather(boolean enableWeather);

    /**
     * Gets whether or not CraftBukkit is keeping the chunks for this world in memory.
     *
     * @return True if CraftBukkit is keeping spawn chunks in memory.
     */
    boolean isKeepingSpawnInMemory();

    /**
     * If true, tells CraftBukkit to keep a worlds spawn chunks loaded in memory (default: true)
     * If not, CraftBukkit will attempt to free memory when players have not used that world.
     * This will not happen immediately.
     *
     * @param keepSpawnInMemory If true, CraftBukkit will keep the spawn chunks loaded in memory.
     */
    void setKeepSpawnInMemory(boolean keepSpawnInMemory);

    /**
     * Gets the spawn location of this world.
     *
     * @return The spawn location of this world.
     */
    @NotNull
    FacingCoordinates getSpawnLocation();

    /**
     * Sets the spawn location for a world.
     *
     * @param spawnLocation The spawn location for a world.
     */
    void setSpawnLocation(@NotNull FacingCoordinates spawnLocation);

    /**
     * Gets whether or not the hunger level of players will go down in a world.
     *
     * @return True if it will go down, false if it will remain steady.
     */
    boolean getHunger();

    /**
     * Sets whether or not the hunger level of players will go down in a world.
     *
     * @param hungerEnabled True if hunger will go down, false to keep it at
     *                      the level they entered a world with.
     */
    void setHunger(boolean hungerEnabled);

    /**
     * Gets the GameMode of this world.
     *
     * @return The GameMode of this world.
     */
    @NotNull
    GameMode getGameMode();

    /**
     * Sets the game mode of this world.
     *
     * @param gameMode The new {@link GameMode}.
     * @return True if the game mode was successfully changed, false if not.
     */
    boolean setGameMode(@NotNull GameMode gameMode);

    /**
     * Gets the amount of currency it requires to enter this world.
     *
     * @return The amount it costs to enter this world.
     */
    double getPrice();

    /**
     * Sets the price for entry to this world.
     * You can think of this like an amount.
     * The type can be set with {@link #setCurrency(int)}
     *
     * @param price The Amount of money/item to enter the world.
     */
    void setPrice(double price);

    /**
     * Gets the Type of currency that will be used when users enter this world.
     *
     * @return The Type of currency that will be used when users enter this world.
     */
    int getCurrency();

    /**
     * Sets the type of item that will be required given the price is not 0.
     * Use -1 to use an AllPay economy, or any valid itemid
     *
     * @param item The Type of currency that will be used when users enter this world.
     */
    void setCurrency(int item);

    /**
     * Gets the world players will respawn in if they die in this one.
     *
     * @return A world that exists on the server.
     */
    @NotNull
    String getRespawnToWorld();

    /**
     * Sets the world players will respawn in if they die in this one.
     * Returns true upon success, false upon failure.
     *
     * @param respawnWorld The name of a world that exists on the server.
     * @return True if respawnWorld existed, false if not.
     */
    boolean setRespawnToWorld(@NotNull String respawnWorld);

    /**
     * Gets the scaling value of this world.Really only has an effect if you use
     * Multiverse-NetherPortals.
     *
     * @return This world's non-negative, non-zero scale.
     */
    double getScaling();

    /**
     * Sets the scale of this world. Really only has an effect if you use
     * Multiverse-NetherPortals.
     *
     * @param scaling A scaling value, cannot be negative or 0.
     * @return Whether the scale was set successfully.
     */
    boolean setScaling(double scaling);

    /**
     * Gets whether or not a world will auto-heal players if the difficulty is on peaceful.
     *
     * @return True if the world should heal (default), false if not.
     */
    boolean getAutoHeal();

    /**
     * Sets whether or not a world will auto-heal players if the difficulty is on peaceful.
     *
     * @param heal True if the world will heal.
     */
    void setAutoHeal(boolean heal);

    /**
     * Gets whether or not Multiverse should auto-adjust the spawn for this world.
     *
     * @return True if Multiverse should adjust the spawn, false if not.
     */
    boolean getAdjustSpawn();

    /**
     * Sets whether or not Multiverse should auto-adjust the spawn for this world.
     *
     * @param adjust True if multiverse should adjust the spawn, false if not.
     */
    void setAdjustSpawn(boolean adjust);

    /**
     * Gets whether or not Multiverse should auto-load this world.
     *
     * @return True if Multiverse should auto-load this world.
     */
    boolean getAutoLoad();

    /**
     * Sets whether or not Multiverse should auto-load this world.
     * <p>
     * True is default.
     *
     * @param autoLoad True if multiverse should auto load this world, false if not.
     */
    void setAutoLoad(boolean autoLoad);

    /**
     * Gets whether or not a player who dies in this world will respawn in their
     * bed or follow the normal respawn pattern.
     *
     * @return True if players dying in this world should respawn at their bed.
     */
    boolean getBedRespawn();

    /**
     * Sets whether or not a player who dies in this world will respawn in their
     * bed or follow the normal respawn pattern.
     * <p>
     * True is default.
     *
     * @param bedRespawn True if players dying in this world respawn at their bed.
     */
    void setBedRespawn(boolean bedRespawn);

    /**
     * Sets the player limit for this world after which players without an override
     * permission node will not be allowed in. A value of -1 or less signifies no limit
     *
     * @param limit The new limit
     */
    void setPlayerLimit(int limit);

    /**
     * Gets the player limit for this world after which players without an override
     * permission node will not be allowed in. A value of -1 or less signifies no limit
     *
     * @return The player limit
     */
    int getPlayerLimit();

    /**
     * Same as {@link #getTime()}, but returns a string.
     * @return The time as a short string: 12:34pm
     */
    @NotNull
    String getTime();

    /**
     * Sets the current time in a world.
     * <p>
     * This method will take the following formats:
     * 11:37am
     *  4:30p
     *  day(morning), night, noon, midnight
     *
     * @param timeAsString The formatted time to set the world to.
     * @return True if the time was set, false if not.
     */
    boolean setTime(@NotNull String timeAsString);

    /**
     * Sets The types of portals that are allowed in this world.
     *
     * @param type The type of portals allowed in this world.
     */
    void allowPortalMaking(@NotNull PortalType type);

    /**
     * Gets which type(s) of portals are allowed to be constructed in this world.
     *
     * @return The type of portals that are allowed.
     */
    @NotNull
    PortalType getAllowedPortals();

    // properties that are not "getter+setter" style
    /**
     * Gets a list of all the worlds that players CANNOT travel to from this world,
     * regardless of their access permissions.
     *
     * @return A List of world names.
     */
    @NotNull
    List<String> getWorldBlacklist();

    void save() throws PluginBaseException;

    /**
     * Returns a collection containing the players logged in and in this world at the time of calling.
     *
     * @return A collection containing the players logged in and in this world at the time of calling.
     */
    @NotNull
    Collection<BasePlayer> getPlayers();

    // animals&monster stuff
    long getTicksPerAnimalSpawn();

    void setTicksPerAnimalSpawn(final long ticks);

    long getTicksPerMonsterSpawn();

    void setTicksPerMonsterSpawn(final long ticks);

    int getAnimalSpawnLimit();

    void setAnimalSpawnLimit(final int limit);

    int getMonsterSpawnLimit();

    void setMonsterSpawnLimit(final int limit);

    int getAmbientSpawnLimit();

    void setAmbientSpawnLimit(final int limit);

    int getWaterAnimalSpawnLimit();

    void setWaterAnimalSpawnLimit(final int limit);

    boolean isPreventingSpawnsList();

    void setPreventingSpawnsList(final boolean prevent);

    Map<String, SpawnException> getSpawnExceptions();

    void addOrUpdateSpawnException(@NotNull final SpawnException spawnException);

    void removeSpawnException(@NotNull final String creatureType);
    // end of animal&monster stuff
}

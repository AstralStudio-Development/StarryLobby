package cn.starry.hub.utils.nametag

import org.bukkit.entity.Player

interface CautusAdapter {
    /**
     * Get all of the nametags of other players.
     *
     * @param player to show plates to.
     * @return list of nametags.
     */
    fun getPlate(player: Player?): List<BufferedNametag?>?

    /**
     * Whether or not to display the health below name to a player.
     *
     * @param player to display health values to.
     * @return whether to see health or not.
     */
    fun showHealthBelowName(player: Player?): Boolean
}

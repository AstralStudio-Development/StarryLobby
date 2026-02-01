package cn.starry.hub.utils.nametag;

import dev.jnic.annotations.Include;
import lombok.Getter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Include
@Getter
public class CautusBoard {

    private final Player player;
    private final UUID uuid;

    private CautusHandler cautusHandler;

    private Set<String> bufferedTeams = new HashSet<>();
    private Map<String, List<String>> bufferedPlayers = new ConcurrentHashMap<>();

    /**
     * Nametag Board.
     *
     * @param player that board belongs to.
     * @param cautusHandler instance.
     */
    public CautusBoard(Player player, CautusHandler cautusHandler) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.cautusHandler = cautusHandler;
        this.setup(player);
    }

    /**
     * Setup Nametag Board.
     *
     * @param player that board belongs to.
     */
    private void setup(Player player) {
        Scoreboard scoreboard = this.getScoreboard();

        // Update Bukkit scoreboard.
        player.setScoreboard(scoreboard);
    }

    /**
     * Get Scoreboard Object.
     *
     * @return existing scoreboard if in hook, or create new one.
     */
    public Scoreboard getScoreboard() {
        if (this.cautusHandler.isHook() || player.getScoreboard() != Bukkit.getScoreboardManager().getMainScoreboard()) {
            return player.getScoreboard();
        } else {
            return Bukkit.getScoreboardManager().getNewScoreboard();
        }
    }

    /**
     * Update Health Slot.
     *
     * @param player object.
     * @param scoreboard of player.
     */
    private void updateHealthBelow(Player player, Scoreboard scoreboard) {
        if (this.cautusHandler.getAdapter().showHealthBelowName(player)) {
            if (scoreboard.getObjective(DisplaySlot.BELOW_NAME) == null) {
                Objective objective = scoreboard.registerNewObjective("showhealth", "health");
                objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
                objective.setDisplayName(ChatColor.RED + StringEscapeUtils.unescapeJava("❤"));
                // Ensures that 0 isn't displayed if they haven't lost health.
                for (Player loopPlayer : Bukkit.getOnlinePlayers()) {
                    objective.getScore(loopPlayer).setScore((int) Math.floor(loopPlayer.getHealth()));
                }
            }
        } else {
            if (scoreboard.getObjective(DisplaySlot.BELOW_NAME) != null) {
                Objective objective = scoreboard.getObjective(DisplaySlot.BELOW_NAME);
                objective.unregister();
            }
        }
    }

    /**
     * Get's or creates a team for the scoreboard.
     *
     * @param scoreboard of team.
     * @param name of team.
     * @return new or existing team.
     */
    private Team getOrRegisterTeam(Scoreboard scoreboard, String name) {
        Team team = scoreboard.getTeam(name);

        if (team == null) {
            team = scoreboard.registerNewTeam(name);
        }

        return team;
    }

    /**
     * Update Nametags slot.
     *
     * @param player object.
     * @param scoreboard of player.
     */
    private void updateNametags(Player player, Scoreboard scoreboard) {
        List<BufferedNametag> nametags = this.cautusHandler.getAdapter().getPlate(player);

        if (nametags == null) {
            return;
        }

        Set<String> toReturn = new HashSet<>();
        Map<String, List<String>> strings = new HashMap<>();

        for (BufferedNametag bufferedNametag : nametags) {
            Team team = this.getOrRegisterTeam(scoreboard, bufferedNametag.groupName());

            toReturn.add(team.getName());
            this.getBufferedTeams().remove(team.getName());

            String prefix = bufferedNametag.prefix() != null ? bufferedNametag.prefix() : ChatColor.RESET.toString();
            String suffix = bufferedNametag.suffix() != null ? bufferedNametag.suffix() : ChatColor.RESET.toString();

            if (!team.getPrefix().equals(prefix)) {
                team.setPrefix(prefix);
            }
            if (!team.getSuffix().equals(suffix)) {
                team.setSuffix(suffix);
            }

            //High version support @ Stalyer
            if (prefix.length() >= 2) {
                ChatColor color = ChatColor.getByChar(prefix.substring(1, 2));
                if (color != null && team.getColor() != color) {
                    team.setColor(color);
                }
            }

            if (bufferedNametag.player() != null) {
                if (!team.hasEntry(bufferedNametag.player().getName())) {
                    team.addEntry(bufferedNametag.player().getName());
                }
                strings.computeIfAbsent(team.getName(), k -> new ArrayList<>()).add(bufferedNametag.player().getName());
            }

            // Friendly Invisibility.
            if (team.canSeeFriendlyInvisibles() != bufferedNametag.friendlyInvis()) {
                team.setCanSeeFriendlyInvisibles(bufferedNametag.friendlyInvis());
            }
        }

        // Unregister teams that are no longer in use.
        for (String newGroupName : this.getBufferedTeams()) {
            Team team = scoreboard.getTeam(newGroupName);

            if (team == null) {
                continue;
            }

            for (String entry : team.getEntries()) {
                team.removeEntry(entry);
            }
            team.unregister();
        }

        this.getBufferedTeams().clear();
        this.getBufferedTeams().addAll(toReturn);

        // Clean out members who are no longer in the team.
        for (String teamName : this.getBufferedTeams()) {
            List<String> members = strings.get(teamName);
            Team team = scoreboard.getTeam(teamName);

            if (team == null) {
                continue;
            }

            for (String entry : team.getEntries()) {
                if (members != null && members.contains(entry)) {
                    continue;
                }
                team.removeEntry(entry);
            }
        }
    }

    /**
     * Update Health and Nametag slots.
     */
    public void update() {
        Scoreboard scoreboard = this.getScoreboard();

        this.updateHealthBelow(this.player, scoreboard);
        this.updateNametags(this.player, scoreboard);
    }

    /**
     * Cleanup Board.
     */
    public void cleanup() {
        this.bufferedPlayers.clear();
        this.bufferedTeams.clear();
    }

}


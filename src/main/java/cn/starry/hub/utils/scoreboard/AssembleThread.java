package cn.starry.hub.utils.scoreboard;

import cn.starry.hub.Main;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collections;
import java.util.List;

public class AssembleThread extends Thread {

    private final Assemble assemble;
    protected int taskId;

    /**
     * Assemble Thread.
     *
     * @param assemble instance.
     */
    AssembleThread(Assemble assemble) {
        this.assemble = assemble;
        Plugin protocolLib = Bukkit.getPluginManager().getPlugin("ProtocolLib");
        this.taskId = protocolLib.getDescription().getVersion().startsWith("5") ? Bukkit.getScheduler().runTaskTimer(Main.getInstance(), this, assemble.getTicks(), assemble.getTicks()).getTaskId() : Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), this, assemble.getTicks(), assemble.getTicks()).getTaskId();
    }

    @Override
    @SneakyThrows
    public void run() {
        this.tick();
    }

    /**
     * Tick logic for thread.
     */
    private void tick() {
        for (Player player : this.assemble.getPlugin().getServer().getOnlinePlayers()) {
            try {
                List<String> newLines;
                AssembleBoard board = this.assemble.getBoards().get(player.getUniqueId());
                if (board == null) continue;
                Scoreboard scoreboard = board.getScoreboard();
                Objective objective = board.getObjective();
                if (scoreboard == null || objective == null) continue;
                String title = ChatColor.translateAlternateColorCodes('&', this.assemble.getAdapter().getTitle(player));
                if (!objective.getDisplayName().equals(title)) {
                    objective.setDisplayName(title);
                }
                if ((newLines = this.assemble.getAdapter().getLines(player)) == null || newLines.isEmpty()) {
                    board.getEntries().forEach(AssembleBoardEntry::remove);
                    board.getEntries().clear();
                } else {
                    if (newLines.size() > 15) {
                        newLines = this.assemble.getAdapter().getLines(player).subList(0, 15);
                    }
                    if (!this.assemble.getAssembleStyle().isDescending()) {
                        Collections.reverse(newLines);
                    }
                    if (board.getEntries().size() > newLines.size()) {
                        for (int i = newLines.size(); i < board.getEntries().size(); ++i) {
                            AssembleBoardEntry entry = board.getEntryAtPosition(i);
                            if (entry == null) continue;
                            entry.remove();
                        }
                    }
                    int cache = this.assemble.getAssembleStyle().getStartNumber();
                    for (int i = 0; i < newLines.size(); ++i) {
                        AssembleBoardEntry entry = board.getEntryAtPosition(i);
                        String line = ChatColor.translateAlternateColorCodes('&', newLines.get(i));
                        if (entry == null) {
                            entry = new AssembleBoardEntry(board, line, i);
                        }
                        entry.setText(line);
                        entry.setup();
                        entry.send(this.assemble.getAssembleStyle().isDescending() ? cache-- : cache++);
                    }
                }
                if (player.getScoreboard() == scoreboard || this.assemble.isHook()) continue;
                player.setScoreboard(scoreboard);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new AssembleException("There was an error updating " + player.getName() + "'s scoreboard.");
            }
        }
    }

}

package ca.slackinjack.fasterplacement;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class AddChatMessage {
    private final Minecraft mc = Minecraft.getMinecraft();
    
    public void add(String message, int messageType) {
        if (mc.thePlayer != null) {
            EnumChatFormatting chatFormat = null;
            switch (messageType) {
                case 0:
                    chatFormat = EnumChatFormatting.GREEN;
                    break;
                case 1:
                    chatFormat = EnumChatFormatting.RED;
                    break;
                case 2:
                    chatFormat = EnumChatFormatting.GOLD;
                    break;
            }
            if (chatFormat != null) {
                mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "[FP] " + chatFormat + message));
            }
        }
    }

    public void addFormattedMessage(String message) {
        if (mc.thePlayer != null) {
            mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "[FP] " + message));
        }
    }
}

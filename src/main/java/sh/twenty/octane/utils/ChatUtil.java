package sh.twenty.octane.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ChatUtil {



    public static Component color(String message) {
        if (message == null || message.isEmpty()) {
            return Component.empty();
        }

        return LegacyComponentSerializer.legacyAmpersand().deserialize(message);
    }
}

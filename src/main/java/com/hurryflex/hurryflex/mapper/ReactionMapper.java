package com.hurryflex.hurryflex.mapper;

import com.hurryflex.hurryflex.model.ReactionType;

public class ReactionMapper {

    public static String toEmoji(ReactionType type) {
        return switch (type) {
            case LIKE -> "👍";
            case LOVE -> "❤️";
            case CARE -> "🥰";
            case HAHA -> "😂";
            case WOW -> "😲";
            case SAD -> "😥";
            case ANGRY -> "😡";
            case DISGUST -> "🤮";
        };
    }
}
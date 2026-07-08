package com.hurryflex.hurryflex.model;

public enum ReactionType {

    LIKE("👍"),
    LOVE("❤️"),
    CARE("🥰"),
    HAHA("😂"),
    WOW("😲"),
    SAD("😥"),
    DISGUST("🤮"),
    ANGRY("😡");

    private final String emoji;

    ReactionType(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
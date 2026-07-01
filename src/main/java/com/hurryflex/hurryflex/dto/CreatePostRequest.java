package com.hurryflex.hurryflex.dto;

public class CreatePostRequest {

    private String content;
    private String imageUrl;

    public CreatePostRequest() {}

    public CreatePostRequest(String content, String imageUrl) {
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
package com.hurryflex.hurryflex.dto;

import org.springframework.web.multipart.MultipartFile;

public class CreateCommentRequest {

    private Long postId;

    // text comment
    private String content;

    // voice comment
    private boolean voice;

    private MultipartFile audioFile;

    private Integer durationSeconds;

    // getters & setters

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isVoice() {
        return voice;
    }

    public void setVoice(boolean voice) {
        this.voice = voice;
    }

    public MultipartFile getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(MultipartFile audioFile) {
        this.audioFile = audioFile;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }
}
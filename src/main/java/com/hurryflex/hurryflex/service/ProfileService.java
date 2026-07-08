package com.hurryflex.hurryflex.service;

import com.hurryflex.hurryflex.dto.ProfileResponse;

public interface ProfileService {

    ProfileResponse getMyProfile(String email);
}
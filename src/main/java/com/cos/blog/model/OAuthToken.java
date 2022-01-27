package com.cos.blog.model;

import lombok.Data;

@Data
public class OAuthToken {
	private String access_token, token_type, refresh_token, scope;
	private int expires_in, refresh_token_expires_in;
}

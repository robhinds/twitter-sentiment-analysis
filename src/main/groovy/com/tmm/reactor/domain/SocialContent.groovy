package com.tmm.reactor.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
class SocialContent {
	public enum SocialProvider { TWITTER }
	
	private String text
	private SocialProvider provider
	private String handle
	private String postDate
	private Map additionalProperties
}

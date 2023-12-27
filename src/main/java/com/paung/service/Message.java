package com.paung.service;

import java.time.Instant;

public record Message(String username, String text, Instant time) {
}

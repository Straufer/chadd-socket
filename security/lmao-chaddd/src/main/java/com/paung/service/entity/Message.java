package com.paung.service.entity;

import java.time.Instant;

public record Message(String username, String msg, Instant time) {
}

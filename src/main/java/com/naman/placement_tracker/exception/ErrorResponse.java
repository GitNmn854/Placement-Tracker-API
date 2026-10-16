package com.naman.placement_tracker.exception;

public record ErrorResponse(int status, String message, long timestamp) {}
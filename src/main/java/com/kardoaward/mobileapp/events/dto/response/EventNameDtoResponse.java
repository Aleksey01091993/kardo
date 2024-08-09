package com.kardoaward.mobileapp.events.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность для возвращения на главную страницу.")
public record EventNameDtoResponse(
        Long id,
        String name) {}

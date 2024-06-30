package com.example.moviestar_p.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long no;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String title;

    @NotEmpty
    private String content;

    private String imagePath;
}

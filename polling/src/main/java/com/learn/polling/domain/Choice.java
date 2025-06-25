package com.learn.polling.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "Choices")
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String text;

    @ManyToOne( optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name="poll_id" ,  nullable = false)
    private Poll poll ;

}

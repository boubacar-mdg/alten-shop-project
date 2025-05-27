package com.alten.shop.tokens.models.entities;

import com.alten.shop.tokens.models.enums.TokenType;
import com.alten.shop.users.models.entities.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TOKENS")
public class Token {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TOKENS")
  @SequenceGenerator(name = "SEQ_TOKENS", sequenceName = "SEQ_TOKENS", allocationSize = 20, initialValue = 10)
  private Long id;

  @Column(unique = true)
  private String token;

  @Enumerated(EnumType.STRING)
  private TokenType tokenType = TokenType.BEARER;

  private boolean revoked;

  private boolean expired;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}

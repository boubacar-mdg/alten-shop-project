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
  public Integer id;

  @Column(unique = true)
  public String token;

  @Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;

  public boolean revoked;

  public boolean expired;

  @ManyToOne
  @JoinColumn(name = "user_id")
  public User user;
}

package br.com.cantinho.things.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Thing model.
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Thing {

  /**
   * Identification.
   */
  private String id;

  /**
   * Thing name.
   */
  private String name;

  /**
   * Thing value in gems.
   */
  private Integer gems;

}

package br.com.cantinho.things.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Thing {

  /**
   * Identification.
   */
  @Id
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

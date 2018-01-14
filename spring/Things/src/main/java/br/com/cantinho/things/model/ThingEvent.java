package br.com.cantinho.things.model;

import lombok.*;

import java.util.Date;

/**
 * An event related to thing.
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ThingEvent {

  /**
   * The thing.
   */
  private Thing thing;

  /**
   * Date when occurs the event.
   */
  private Date date;

}

package br.com.cantinho.things.model;

import java.util.Date;
import lombok.*;

/**
 * An event related to thing.
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

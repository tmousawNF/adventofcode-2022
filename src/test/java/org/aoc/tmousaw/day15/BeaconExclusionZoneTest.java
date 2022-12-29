package org.aoc.tmousaw.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class BeaconExclusionZoneTest {

  @Test
  void testBeaconExclusionZone() throws IOException {
    BeaconExclusionZone beaconExclusionZone = new BeaconExclusionZone(2000000, 4000000);
    beaconExclusionZone.solve();

    assertEquals("4811413", beaconExclusionZone.getAnswers().get(0).getValue());
    assertEquals("13171855019123", beaconExclusionZone.getAnswers().get(1).getValue());
  }

  @Test
  void testBeaconExclusionZoneSample() throws IOException {
    BeaconExclusionZone beaconExclusionZone = new BeaconExclusionZone("sample.txt", 10, 20);
    beaconExclusionZone.solve();

    assertEquals("26", beaconExclusionZone.getAnswers().get(0).getValue());
    assertEquals("56000011", beaconExclusionZone.getAnswers().get(1).getValue());
  }
}

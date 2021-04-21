package io.resourcepool.ssdp;

import static io.resourcepool.ssdp.client.SsdpParams.UTF_8;

import java.net.DatagramPacket;

import org.junit.Assert;
import org.junit.Test;

import io.resourcepool.ssdp.client.SsdpParams;
import io.resourcepool.ssdp.client.parser.ResponseParser;
import io.resourcepool.ssdp.client.response.SsdpResponse;

/**
 * This checks The parsing of Response SSDP Datagrams
 *
 * @author Loïc Ortola on 08/01/2018
 */
public class SSDPResponseParserTest {

  /**
   * This tests checks that headers with multiple semicolumns actually parse the right value. This
   * is related to issue #7
   *
   * @see "https://github.com/resourcepool/ssdp-client/issues/7"
   */
  @Test
  public void testParserHeaders() throws InterruptedException {

    StringBuilder sb = new StringBuilder("NOTIFY * HTTP/1.1\r\n");
    sb.append("HOST:239.255.255.250:1900\r\n")
        .append("NORMAL: classic\r\n")
        .append("NORMAL2:nospace\r\n")
        .append("NORMAL3: classic with spaces after \r\n")
        .append("INFO: 'shouldbeinvalue: thistoo'\r\n");

    byte[] content = sb.toString().getBytes(UTF_8);
    SsdpParams params = new SsdpParams();
    DatagramPacket datagramPacket = new DatagramPacket(content, content.length,
        params.getSsdpMulticastAddress(), params.getSsdpMulticastPort());

    SsdpResponse response = ResponseParser.parse(datagramPacket);
    Assert.assertTrue(response.getHeaders().containsKey("NORMAL"));
    Assert.assertTrue(response.getHeaders().containsValue("classic"));
    Assert.assertTrue(response.getHeaders().containsKey("NORMAL2"));
    Assert.assertTrue(response.getHeaders().containsValue("nospace"));
    Assert.assertTrue(response.getHeaders().containsKey("NORMAL3"));
    Assert.assertTrue(response.getHeaders().containsValue("classic with spaces after"));
    // Issue #7
    Assert.assertTrue(response.getHeaders().containsKey("INFO"));
    Assert.assertTrue(response.getHeaders().containsValue("'shouldbeinvalue: thistoo'"));
  }

}

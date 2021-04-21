package io.resourcepool.ssdp.client.request;

import io.resourcepool.ssdp.client.SsdpParams;
import io.resourcepool.ssdp.model.DiscoveryOptions;

import java.net.DatagramPacket;

import static io.resourcepool.ssdp.client.SsdpParams.UTF_8;

/**
 * This creates the SSDP Discovery Request.
 *
 * @author Loïc Ortola on 05/08/2017
 */
public abstract class SsdpDiscovery {

  /**
   * Get Datagram from serviceType.
   *
   * @param serviceType the serviceType
   * @param options the Request Discovery Options
   * @param params the SsdpParams to customize multicast target
   * @return the DatagramPacket matching the search request
   */
  public static DatagramPacket getDatagram(String serviceType, DiscoveryOptions options,
      SsdpParams params) {
    StringBuilder sb = new StringBuilder("M-SEARCH * HTTP/1.1\r\n");
    sb.append("HOST: " + params.getSsdpMulticastAddress().getHostAddress() + ":" + params.getSsdpMulticastPort() + "\r\n");
    sb.append("MAN: \"ssdp:discover\"\r\n");
    sb.append("MX: " + options.getMaxWaitTimeSeconds() + "\r\n");
    if (options.getUserAgent() != null) {
      sb.append("USER-AGENT: " + options.getUserAgent() + "\r\n");
    }    
    sb.append((serviceType == null || serviceType.trim().isEmpty()) ? "ST: ssdp:all\r\n" : "ST: " + serviceType + "\r\n");
    sb.append("\r\n");

    byte[] content = sb.toString().getBytes(UTF_8);
    return new DatagramPacket(content, content.length, params.getSsdpMulticastAddress(), params.getSsdpMulticastPort());
  }
}

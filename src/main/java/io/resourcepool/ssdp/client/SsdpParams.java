package io.resourcepool.ssdp.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * This holds the parameters of SSDP protocol (multicast ip and port).
 *
 * @author Loïc Ortola on 05/08/2017
 */
public class SsdpParams {

  public static final Charset UTF_8 = StandardCharsets.UTF_8;
  public static final int DEFAULT_SSDP_PORT = 1900;
  public static final String DEFAULT_MULTICAST_IPV4 = "239.255.255.250";

  private final InetAddress ssdpMulticastIpv4Address;
  private final int ssdpMulticastPort;
  private final boolean onlyLocalAddress;

  public SsdpParams() {
    this(DEFAULT_MULTICAST_IPV4);
  }

  public SsdpParams(String ssdpMulticastIpv4Address) {
    this(ssdpMulticastIpv4Address, DEFAULT_SSDP_PORT, false);

  }

  public SsdpParams(String ssdpMulticastIpv4Address, int ssdpPort, boolean onlyLocalAddress) {
    try {
      this.onlyLocalAddress = onlyLocalAddress;
      this.ssdpMulticastPort = ssdpPort;
      this.ssdpMulticastIpv4Address = InetAddress.getByName(ssdpMulticastIpv4Address);
    } catch (UnknownHostException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * @return the Ssdp Multicast Ip Address
   */
  public InetAddress getSsdpMulticastAddress() {
    return ssdpMulticastIpv4Address;
  }

  /**
   * @return the Ssdp Port
   */
  public int getSsdpMulticastPort() {
    return ssdpMulticastPort;
  }

  public boolean onlyLocalAddress() {
    return onlyLocalAddress;
  }
}

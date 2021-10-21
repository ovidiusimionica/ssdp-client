package io.resourcepool.ssdp;

import static io.resourcepool.ssdp.client.SsdpParams.DEFAULT_MULTICAST_IPV4;
import static io.resourcepool.ssdp.client.SsdpParams.DEFAULT_SSDP_LISTEN_PORT;
import static org.junit.Assert.assertFalse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.client.SsdpParams;
import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.DiscoveryRequest;
import io.resourcepool.ssdp.model.SsdpRequest;
import io.resourcepool.ssdp.model.SsdpService;
import io.resourcepool.ssdp.model.SsdpServiceAnnouncement;

/**
 * Testing SSDP Socket receive
 *
 * @author Loïc Ortola on 08/01/2018
 */
public class SSDPSocketTest {

  /**
   * Warning: this test only verifies there is no failure during a SSDP check. It does not check
   * whether SSDP discovery actually works
   *
   * @throws InterruptedException if a thread error occurs
   */
  @Test
  public void testSSDPReceive() throws InterruptedException {
    testRun(SsdpClient.create());
  }

  private void testRun(SsdpClient client) throws InterruptedException {
    final CountDownLatch lock = new CountDownLatch(1);
    DiscoveryRequest all = SsdpRequest.discoverAll();
    client.discoverServices(all, new DiscoveryListener() {
      @Override
      public void onServiceDiscovered(SsdpService service) {
        System.out.println("Found service: " + service);
      }

      @Override
      public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {
        System.out.println("Service announced something: " + announcement);
      }

      @Override
      public void onFailed(Exception ex) {
        System.err.println("Service failed to announce something: " + ex);
        throw new IllegalStateException(ex);
      }
    });

    assertFalse(lock.await(10, TimeUnit.SECONDS));
  }


  /**
   * Warning: this test only verifies there is no failure during a SSDP check. It does not check
   * whether SSDP discovery actually works
   *
   * @throws InterruptedException if a thread error occurs
   */
  @Test
  public void testSSDPReceiveCustomMulticast() throws InterruptedException {
    testRun(
        SsdpClient.create(new SsdpParams(DEFAULT_MULTICAST_IPV4, 1800, DEFAULT_SSDP_LISTEN_PORT, true))
    );

  }

}

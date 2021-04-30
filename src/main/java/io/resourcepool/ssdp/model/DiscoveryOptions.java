package io.resourcepool.ssdp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the discovery request options.
 * Defaults are shown in static final fields.
 */
public class DiscoveryOptions {

  private static final Long DEFAULT_INTERVAL_BETWEEN_REQUESTS = 10000L;
  private static final Integer DEFAULT_MAX_WAIT_TIME_SECONDS = 3;
  private static final String DEFAULT_USER_AGENT = "Resourcepool SSDP Client";

  private Long intervalBetweenRequests = DEFAULT_INTERVAL_BETWEEN_REQUESTS;
  private Integer maxWaitTimeSeconds = DEFAULT_MAX_WAIT_TIME_SECONDS;
  private String userAgent = DEFAULT_USER_AGENT;
  private Map<String,String> additionalSearchHeaders = new HashMap<>();

  public Long getIntervalBetweenRequests() {
    return intervalBetweenRequests;
  }

  public Integer getMaxWaitTimeSeconds() {
    return maxWaitTimeSeconds;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public Map<String, String> getAdditionalSearchHeaders() {
    return additionalSearchHeaders;
  }

  // BEGIN GENERATED CODE

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private Long intervalBetweenRequests = DEFAULT_INTERVAL_BETWEEN_REQUESTS;
    private Integer maxWaitTimeSeconds = DEFAULT_MAX_WAIT_TIME_SECONDS;
    private String userAgent = DEFAULT_USER_AGENT;


    private Map<String,String> additionalSearchHeaders = new HashMap<>();

    private Builder() {
    }

    /**
     * Interval between requests in milliseconds.
     * Defaults to 10 000 ms
     * @param intervalBetweenRequests the interval between requests in ms
     * @return the current builder
     */
    public Builder intervalBetweenRequests(Long intervalBetweenRequests) {
      if (intervalBetweenRequests < 10) {
        throw new IllegalArgumentException("Interval between requests must be at least 10 milliseconds");
      }
      this.intervalBetweenRequests = intervalBetweenRequests;
      return this;
    }

    /**
     * Max response time in seconds allowed between request and response from device.
     * MUST be greater than or equal to 1, and SHOULD be less than 5
     * Defaults to 3
     * @param maxWaitTimeSeconds maximum response time in seconds
     * @return the current builder
     */
    public Builder maxWaitTimeSeconds(Integer maxWaitTimeSeconds) {
      if (intervalBetweenRequests < 1) {
        throw new IllegalArgumentException("Max wait time must be at least one second");
      }
      this.maxWaitTimeSeconds = maxWaitTimeSeconds;
      return this;
    }

    /**
     * User agent header used in the request.
     * Defaults to "Resourcepool SSDP Client"
     * If set to null, user agent header will not be added
     * @param userAgent the user agent
     * @return the current builder
     */
    public Builder userAgent(String userAgent) {
      this.userAgent = userAgent;
      return this;
    }

    /**
     * Adds an extra M-SEARCH header
     *
     * @param header the key string
     * @param value the value for that key
     * @return the current builder
     */
    public Builder addSearchHeader(String header, String value) {
      this.additionalSearchHeaders.put(header, value);
      return this;
    }

    /**
     * Set additional M-SEARCH headers
     *
     * @param headers must not be null
     */
    public Builder setSearchHeaders(Map<String, String> headers) {
      if (headers != null) {
        this.additionalSearchHeaders.clear();
        this.additionalSearchHeaders.putAll(headers);
      }
      return this;
    }

    public DiscoveryOptions build() {
      DiscoveryOptions discoveryOptions = new DiscoveryOptions();
      discoveryOptions.maxWaitTimeSeconds = this.maxWaitTimeSeconds;
      discoveryOptions.intervalBetweenRequests = this.intervalBetweenRequests;
      discoveryOptions.userAgent = this.userAgent;
      discoveryOptions.additionalSearchHeaders = this.additionalSearchHeaders;
      return discoveryOptions;
    }
  }

  // END GENERATED CODE
}

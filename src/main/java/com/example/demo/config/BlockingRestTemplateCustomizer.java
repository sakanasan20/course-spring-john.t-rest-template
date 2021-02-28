package com.example.demo.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
	
	private final Integer maxTotal;
	private final Integer defaultMaxPerRoute;
	private final Integer connectionRequestTimeout;	
	private final Integer socketTimeout;
	
	public BlockingRestTemplateCustomizer(
			@Value("${demo.apache.maxtotal}")Integer maxTotal, 
			@Value("${demo.apache.defaultmaxperroute}")Integer defaultMaxPerRoute,
			@Value("${demo.apache.connectionrequesttimeout}")Integer connectionRequestTimeout, 
			@Value("${demo.apache.sockettimeout}")Integer socketTimeout) {
		
		this.maxTotal = maxTotal;
		this.defaultMaxPerRoute = defaultMaxPerRoute;
		this.connectionRequestTimeout = connectionRequestTimeout;
		this.socketTimeout = socketTimeout;
	}
	
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = 
				new PoolingHttpClientConnectionManager();
		
		poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		
		RequestConfig requestConfig = RequestConfig
				.custom()
				.setConnectionRequestTimeout(connectionRequestTimeout)
				.setSocketTimeout(socketTimeout)
				.build();
		
		CloseableHttpClient closeableHttpClient = HttpClients
				.custom()
				.setConnectionManager(poolingHttpClientConnectionManager)
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
				.setDefaultRequestConfig(requestConfig)
				.build();
				
		return new HttpComponentsClientHttpRequestFactory(closeableHttpClient);
	}

	@Override
	public void customize(RestTemplate restTemplate) {
		
		restTemplate.setRequestFactory(this.clientHttpRequestFactory());
	}

}

package com.capgemini.Analyze.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.capgemini.Analyze.dto.DeleteModelDTO;
import com.capgemini.Analyze.exception.QEPException;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

@Service
public class PythonApiService {

    private static final Logger logger = LogManager.getLogger(PythonApiService.class);
    private final RestTemplate restTemplate;

    public PythonApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> getRequest(String url, Map<String, String> params,
                                            ParameterizedTypeReference<T> responseType) throws QEPException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // Ensure we accept JSON
            // responses.
            HttpEntity<?> entity = new HttpEntity<>(headers);

            URI uri = buildUri(url, params);
            ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.GET, entity, responseType);

            logger.info("GET request successful for URL: {}", uri); // Log successful requests.
            return response;
        } catch (HttpStatusCodeException e) {
            logger.error("HTTP Status Code Exception in GET request: {}", e.getMessage());
            throw new QEPException("Invoking Python Server Application Failed: Method-Type :GET " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            logger.error("Rest Client Exception in GET request: {}", e.getMessage());
            throw new QEPException("Invoking Python Server Application Failed: Method-Type :GET", e);
        }
    }

    public <T, R> ResponseEntity<T> postRequest(String url, R requestBody, ParameterizedTypeReference<T> responseType)
            throws QEPException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<R> request = new HttpEntity<>(requestBody, headers);

            return restTemplate.exchange(url, HttpMethod.POST, request, responseType);
        } catch (HttpStatusCodeException e) {
            logger.error("HTTP Status Code Exception in POST request: {}", e.getMessage());
            throw new QEPException("Invoking Python Server Application Failed: Method-Type :POST " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            logger.error("Rest Client Exception in POST request: {}", e.getMessage());
            throw new QEPException("Invoking Python Server Application Failed: Method-Type :POST ", e);
        }
    }

    public <T, R> ResponseEntity<T> putRequest(String url, R requestBody, ParameterizedTypeReference<T> responseType)
            throws QEPException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<R> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, request, responseType);

            logger.info("PUT request successful for URL: {}", url); // Log successful requests.
            return response;
        } catch (HttpStatusCodeException e) {
            logger.error("HTTP Status Code Exception in PUT request: {}", e.getMessage());
            throw new QEPException("Invoking Python Server Application Failed: Method-Type :PUT " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            logger.error("Rest Client Exception in PUT request: {}", e.getMessage());
            throw new QEPException("Invoking Python Server Application Failed: Method-Type :PUT ", e);
        }
    }

    public <T> ResponseEntity<T> deleteRequest(String url, ParameterizedTypeReference<T> responseType)
            throws QEPException {
        try {
            return restTemplate.exchange(url, HttpMethod.DELETE, null, responseType);
        } catch (HttpStatusCodeException e) {
            logger.error("HTTP Status Code Exception in DELETE request: {}", e.getMessage());
            throw new QEPException("Invoking Python Server Application Failed: Method-Type :DELETE " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            logger.error("Rest Client Exception in DELETE request: {}", e.getMessage());
            throw new QEPException("Invoking Python Server Application Failed: Method-Type :DELETE ", e);
        }
    }

    public <T> ResponseEntity<T> deleteRequest(String url, DeleteModelDTO deleteModelDto,
                                               ParameterizedTypeReference<T> responseType) throws QEPException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<DeleteModelDTO> request = new HttpEntity<>(deleteModelDto, headers);
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.DELETE, request, responseType);

            logger.info("DELETE request successful for URL: {}", url); // Log successful requests.
            return response;
        } catch (HttpStatusCodeException e) {
            logger.error("HTTP Status Code Exception in DELETE request: {}", e.getMessage());
            throw new QEPException("Invoking Python Server Application Failed: Method-Type :DELETE " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            logger.error("Rest Client Exception in DELETE request: {}", e.getMessage());
            throw new QEPException("Invoking Python Server Application Failed: Method-Type :DELETE", e);
        }
    }

    private URI buildUri(String url, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (params != null) {
            params.forEach(builder::queryParam);
        }
        return builder.build().toUri();
    }
}

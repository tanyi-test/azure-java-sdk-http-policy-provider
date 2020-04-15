package com.test;

import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
import com.azure.core.http.HttpResponse;
import com.azure.core.http.policy.AfterRetryPolicyProvider;
import com.azure.core.http.policy.HttpPipelinePolicy;
import reactor.core.publisher.Mono;

public class lastHttpPolicyProvider implements AfterRetryPolicyProvider {
    @Override
    public HttpPipelinePolicy create() {
        return new HttpPipelinePolicy() {
            @Override
            public Mono<HttpResponse> process(HttpPipelineCallContext context, HttpPipelineNextPolicy next) {
                System.out.println("Running last");
                return next.process();
            }
        };
    }
}

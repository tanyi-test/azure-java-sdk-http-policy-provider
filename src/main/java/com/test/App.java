package com.test;

import com.azure.core.management.AzureEnvironment;
import com.azure.management.ApplicationTokenCredential;
import com.azure.management.Azure;
import com.azure.management.RestClient;
import com.azure.management.RestClientBuilder;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        final File credFile = new File(System.getenv("AZURE_AUTH_LOCATION"));

        ApplicationTokenCredential credential = ApplicationTokenCredential.fromFile(credFile);

        RestClient restClient = new RestClientBuilder()
                .withBaseUrl(credential.getEnvironment(), AzureEnvironment.Endpoint.RESOURCE_MANAGER)
                .withCredential(credential)
                .withPolicy(new MidHttpPolicy())
                .buildClient();

        Azure azure = Azure.authenticate(restClient, credential.getDomain(), credential.getDefaultSubscriptionId()).withDefaultSubscription();

        azure.resourceGroups().listAsync().blockLast();
    }
}

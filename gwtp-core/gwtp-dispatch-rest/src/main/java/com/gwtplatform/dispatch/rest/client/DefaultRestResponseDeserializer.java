/**
 * Copyright 2013 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.dispatch.rest.client;

import javax.inject.Inject;

import org.jboss.errai.enterprise.client.jaxrs.JacksonTransformer;
import org.jboss.errai.marshalling.client.Marshalling;

import com.google.gwt.http.client.Response;
import com.gwtplatform.dispatch.rest.shared.MetadataType;
import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.gwtplatform.dispatch.shared.ActionException;

public class DefaultRestResponseDeserializer implements RestResponseDeserializer {
    private final ActionMetadataProvider metadataProvider;

    @Inject
    DefaultRestResponseDeserializer(ActionMetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }

    @Override
    public <A extends RestAction<R>, R> R deserialize(A action, Response response) throws ActionException {
        if (isSuccessStatusCode(response)) {
            return getDeserializedResponse(action, response);
        } else {
            throw new ActionException(response.getStatusText());
        }
    }

    protected <R> boolean canDeserialize(Class<R> resultClass) {
        return Marshalling.canHandle(resultClass);
    }

    protected String cleanupJson(String json) {
        return JacksonTransformer.fromJackson(json);
    }

    protected <R> R deserializeValue(Class<R> resultClass, String json) {
        return Marshalling.fromJSON(json, resultClass);
    }

    protected <R> R deserializeCollection(Class<R> resultClass, Class<?> keyClass, String json) {
        return Marshalling.fromJSON(json, resultClass, keyClass);
    }

    protected <R> R deserializeMap(Class<R> resultClass, Class<?> keyClass, Class<?> valueClass, String json) {
        return Marshalling.fromJSON(json, resultClass, keyClass, valueClass);
    }

    private boolean isSuccessStatusCode(Response response) {
        int statusCode = response.getStatusCode();

        return (statusCode >= 200 && statusCode < 300) || statusCode == 304;
    }

    private <R> R getDeserializedResponse(RestAction<R> action, Response response) throws ActionException {
        @SuppressWarnings("unchecked")
        Class<R> resultClass = (Class<R>) metadataProvider.getValue(action, MetadataType.RESPONSE_CLASS);
        Class<?> keyClass = (Class<?>) metadataProvider.getValue(action, MetadataType.KEY_CLASS);
        Class<?> valueClass = (Class<?>) metadataProvider.getValue(action, MetadataType.VALUE_CLASS);
        R result = null;

        if (resultClass != Void.class) {
            if (resultClass != null && canDeserialize(resultClass)) {
                String json = cleanupJson(response.getText());

                if (valueClass != null) {
                    result = deserializeMap(resultClass, keyClass, valueClass, json);
                } else if (keyClass != null) {
                    result = deserializeCollection(resultClass, keyClass, json);
                } else {
                    result = deserializeValue(resultClass, json);
                }
            } else {
                throw new ActionException("Unable to deserialize response. No serializer found.");
            }
        }

        return result;
    }
}

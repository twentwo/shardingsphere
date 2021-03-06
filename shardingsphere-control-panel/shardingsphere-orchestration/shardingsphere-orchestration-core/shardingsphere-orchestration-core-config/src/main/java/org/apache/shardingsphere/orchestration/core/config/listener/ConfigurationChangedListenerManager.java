/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.orchestration.core.config.listener;

import org.apache.shardingsphere.orchestration.repository.api.ConfigCenterRepository;
import org.apache.shardingsphere.orchestration.repository.api.listener.DataChangedEvent.ChangedType;

import java.util.Collection;

/**
 * Configuration changed listener manager.
 */
public final class ConfigurationChangedListenerManager {
    
    private final SchemaChangedListener schemaChangedListener;
    
    private final PropertiesChangedListener propertiesChangedListener;
    
    private final AuthenticationChangedListener authenticationChangedListener;
    
    private final MetricsConfigurationChangedListener metricsConfigurationChangedListener;
    
    private final ClusterConfigurationChangedListener clusterConfigurationChangedListener;
    
    public ConfigurationChangedListenerManager(final String name, final ConfigCenterRepository configCenterRepository, final Collection<String> shardingSchemaNames) {
        schemaChangedListener = new SchemaChangedListener(name, configCenterRepository, shardingSchemaNames);
        propertiesChangedListener = new PropertiesChangedListener(name, configCenterRepository);
        authenticationChangedListener = new AuthenticationChangedListener(name, configCenterRepository);
        metricsConfigurationChangedListener = new MetricsConfigurationChangedListener(name, configCenterRepository);
        clusterConfigurationChangedListener = new ClusterConfigurationChangedListener(name, configCenterRepository);
    }
    
    /**
     * Initialize all configuration changed listeners.
     */
    public void initListeners() {
        schemaChangedListener.watch(ChangedType.UPDATED, ChangedType.DELETED);
        propertiesChangedListener.watch(ChangedType.UPDATED);
        authenticationChangedListener.watch(ChangedType.UPDATED);
        metricsConfigurationChangedListener.watch(ChangedType.UPDATED);
        clusterConfigurationChangedListener.watch(ChangedType.UPDATED);
    }
}

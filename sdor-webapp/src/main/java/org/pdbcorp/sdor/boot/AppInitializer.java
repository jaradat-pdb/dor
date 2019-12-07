/*
 * Copyright 2019 PDB Corp.
 *
 * Proprietary Software built off of open-source software?
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pdbcorp.sdor.boot;

import org.pdbcorp.sdor.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jaradat-pdb
 *
 */
@Slf4j
@Component
public class AppInitializer implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	public AppInitializer(ProductServiceImpl productServiceImpl) {
		if(log.isDebugEnabled())
			log.debug("Load test data maps via product service");
		productServiceImpl.loadTestDataMaps();
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(log.isTraceEnabled())
			log.trace("Context refreshed event occurred at {} triggered by {}", event.getTimestamp(), event.getSource().getClass().getSimpleName());
	}
}

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
package org.pdbcorp.sdor.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jaradat-pdb
 *
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdministrationController {
	@GetMapping(value = {"", "/", "/admin", "/home", "/main"})
	public String getPage() {
		if(log.isTraceEnabled())
			log.trace("Responding to request for admin page");
		return "admin";
	}

	@PostMapping(value = "/initiateShutdown")
	public String initiateShutdown(HttpServletRequest request) {
		String cookie = request.getHeader("Cookie");
		try {
			sendShutdownSignal(cookie);
			return "redirect:/shutdown";
		} catch (IOException e) {
			return "500";
		}
	}

	@GetMapping(value = "/shutdown")
	public String shutdown() {
		log.info("Shutdown initiation successful, goodbye");
		return "shutdown";
	}

	private void sendShutdownSignal(String sessionId) throws IOException {
		if(log.isDebugEnabled())
			log.debug("Sending HTTP POST shutdown signal as requested by root administrator, sessionId: {}", sessionId);
		
		HttpPost httpPost = new HttpPost("http://localhost:8080/actuator/shutdown");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Cookie", sessionId);
		httpPost.setHeader("Content-Type", "application/json");
		
		try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
			if(log.isTraceEnabled())
				log.trace("Constructed HTTP POST shutdown signal, attempting to execute request");
			httpClient.execute(httpPost);
		}
	}
}

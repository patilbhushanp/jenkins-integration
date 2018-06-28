package com.sanbhu.jenkins.integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.offbytwo.jenkins.model.Job;
import com.sanbhu.jenkins.integration.service.JenkinsIntegration;
import com.sanbhu.jenkins.integration.service.impl.JenkinsIntegrationImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		JenkinsIntegration jenkinsIntegration = new JenkinsIntegrationImpl();
		List<String> jobList = jenkinsIntegration.getJobList("bhushanp", "Xpanxion1234");
		System.out.println("Job List : " + jobList);
		Job job = jenkinsIntegration.getJob("bhushanp", "Xpanxion1234", "school-dashboard");
		if(job != null) {
			final Map<String, String> params = new HashMap<String, String>();
			Boolean executionResult = jenkinsIntegration.executeJob(job, params);
			System.out.println("Execution Result :" + executionResult);
		}
	}
}
